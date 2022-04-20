package frame;

import common.Constants;
import entityClass.Archive;
import entityClass.SecurityClassification;
import exception.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName : ArchiveManagementFrame //����
 * @Author : ߲����
 * @Data : 2021/12/4
 */
@SuppressWarnings("all")
public class ArchiveManagementFrame extends BaseFrame {
    private Container container;
    private JButton downloadButton, uploadButton, deleteButton;
    private JScrollPane archiveScrollTable;
    private JTable archiveTable;


    private Object[] columNames;
    private Object[][] archiveInfo;
    private List<Archive> archiveList;

    {
        columNames = new Object[]{"���к�", "�ļ���", "�ϴ���", "�ϴ�ʱ��"};
    }

    public ArchiveManagementFrame() {
        Init();
    }

    public void setFrameByRole() {
        String role = user.getRole();
        if (!role.equals("administrator")) {
            deleteButton.setVisible(false);
        }
    }

    /**
     * ��ʼ������
     */
    private void Init() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(600, 400);
        setTitle("���ڹ���");
        setResizable(true);
        setLayout(null);
        container = getContentPane();

        // ����λ��Ϊ��Ļ����
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);


        //��ʾ�ļ�
        archiveInfo = new Object[20][4];
        archiveTable = new JTable(archiveInfo, columNames);
        try {
            archiveList = archiveService.getAllArchives();
            archiveList.sort(new Comparator<Archive>() {
                @Override
                public int compare(Archive o1, Archive o2) {
                    return (int) o1.getId() - (int) o2.getId();
                }
            });
            int size = archiveList.size();
            for (int i = 0; i < size; i++) {
                archiveInfo[i][0] = archiveList.get(i).getId();
                archiveInfo[i][1] = archiveList.get(i).getFileName();
                archiveInfo[i][2] = archiveList.get(i).getUser().getName();
                archiveInfo[i][3] = archiveList.get(i).getTimestamp();
            }

        } catch (BaseException e) {
            e.printStackTrace();
        }
        archiveTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);


        //���ù������õ���table���뵽ScrollTable��
        archiveScrollTable = new JScrollPane(archiveTable);
        archiveScrollTable.setSize(500, 150);
        archiveScrollTable.setLocation(40, 30);
        container.add(archiveScrollTable);


        //���ذ�ť����
        downloadButton = new JButton("����");
        downloadButton.setToolTipText("����ѡ�еİ��ڵ�ָ��λ��");
        downloadButton.setBounds(100, 220, 100, 20);
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadButtonAction(e);
            }
        });
        container.add(downloadButton);

        //�ϴ������ļ�����
        uploadButton = new JButton("�ϴ�����");
        uploadButton.setToolTipText("�ӱ����ļ����ϴ�");
        uploadButton.setBounds(320, 220, 100, 20);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadButtonAction(e);
            }
        });
        container.add(uploadButton);


        deleteButton = new JButton("ɾ������");
        deleteButton.setToolTipText("ɾ��ѡ�еİ���");
        deleteButton.setBounds(180, 290, 100, 20);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonAction(e);
            }
        });
        container.add(deleteButton);

        setVisible(true);
    }

    private void deleteButtonAction(ActionEvent event) {
        int selectedRow = archiveTable.getSelectedRow() + 1;
        if (selectedRow > archiveList.size()) {
            JOptionPane.showMessageDialog(this, "û��ѡ��");
        } else {
            long id = selectedRow;

            int dialog = JOptionPane.showConfirmDialog(this, "ȷ��ɾ����", "ɾ����ʾ", JOptionPane.OK_CANCEL_OPTION);
            if (dialog == JOptionPane.OK_OPTION) {
                try {
                    archiveService.deleteArchive(id);
                    JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
                    updateArchiveList();
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ������ذ�ť����Ӧ
     * ���û��ѡ���ļ����򵯴���ʾû��ѡ���ļ�
     * ѡ�������������·����ѡ��
     * ���ûѡ������·�������ص�Ĭ��λ��
     *
     * @param event
     */
    private void downloadButtonAction(ActionEvent event) {

        int selectedRow = archiveTable.getSelectedRow() + 1;
        if (selectedRow > archiveList.size()) {
            JOptionPane.showMessageDialog(this, "û��ѡ���ļ�");
        } else {
            JFileChooser fileChooser = new JFileChooser();
            //���ÿ�ѡ��Χ
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showSaveDialog(this);
            //���ѡ�����ȷ�ϣ���������·��������
            if (result == JFileChooser.APPROVE_OPTION) {
                //��ȡѡ�е�·��
                String targetPath = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    archiveService.downloadArchive(selectedRow, targetPath);
                    JOptionPane.showMessageDialog(this, "���سɹ�");
                } catch (BaseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //���ļ�ѡ���֮��û��ѡ��λ�ã������ص�Ĭ��λ��
                int flag = JOptionPane.showConfirmDialog(this, "δѡ����λ��\n�����ص�Ĭ��λ��");
                String targetPath = Constants.DOWNLOAD_PATH;
                if (flag == JOptionPane.OK_OPTION) {
                    try {
                        archiveService.downloadArchive(selectedRow, targetPath);
                        JOptionPane.showMessageDialog(this, "���سɹ�");
                    } catch (BaseException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    /**
     * �ϴ��ļ���ť��Ӧ
     *
     * @param event
     */
    private void uploadButtonAction(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        //����ֻ��ѡ���ļ�
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //����Ĭ��Ϊ��ǰĿ¼
        jFileChooser.setCurrentDirectory(new File("."));
        //�ж��Ƿ�ѡ�����ļ�
        int flag = jFileChooser.showOpenDialog(this);
        if (flag == JFileChooser.APPROVE_OPTION) {
            //��ȡѡ����ļ�
            File file = jFileChooser.getSelectedFile();
            //����default����
            Archive archive = new Archive();
            archive.setId(archiveList.size() + 1);
            archive.setUser(user);
            archive.setFileName(file.getName());
            archive.setTimestamp(new Timestamp(System.currentTimeMillis()));
            archive.setSourcePath(file.getAbsolutePath());
            archive.setAbsolutePath(file.getAbsolutePath());
            archive.setCatalogue("null");
            archive.setTitle(user.getName() + file.getName());
            archive.setKeyword(user.getName() + file.getName());


            //ѡ��security�ȼ�
            JFrame jFrame = new JFrame("ѡ��security�ȼ�");
            jFrame.setLayout(new BorderLayout());
            Toolkit toolkit = getToolkit();
            Dimension dimension = toolkit.getScreenSize();
            jFrame.setSize(150, 150);
            jFrame.setLocation((dimension.width - jFrame.getWidth()) / 2, (dimension.height - jFrame.getHeight()) / 2);

            JPanel jPanel = new JPanel();
            jFrame.add(jPanel, BorderLayout.CENTER);

            JComboBox jComboBox = new JComboBox();
            jComboBox.setSize(100, 30);
            jComboBox.setLocation(50, 50);
            jComboBox.setModel(new DefaultComboBoxModel(new String[]{
                    "A", "B", "C", "D"
            }));
            jPanel.add(jComboBox);

            JButton jButton = new JButton("ȷ��");
            jButton.setBounds(50, 130, 100, 20);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = (String) jComboBox.getSelectedItem();
                    if ("A".equals(s)) {
                        archive.setSecurityClassification(SecurityClassification.A);
                    } else if ("B".equals(s)) {
                        archive.setSecurityClassification(SecurityClassification.B);
                    } else if ("C".equals(s)) {
                        archive.setSecurityClassification(SecurityClassification.C);
                    } else if ("D".equals(s)) {
                        archive.setSecurityClassification(SecurityClassification.D);
                    }
                    jFrame.setVisible(false);
                    try {
                        archiveService.uploadArchive(archive);
                        JOptionPane.showMessageDialog(null, "�ļ�" + file.getName() + "�ϴ��ɹ�");
                        //����archiveList
                        updateArchiveList();
                    } catch (BaseException event) {
                        event.printStackTrace();
                    }
                }
            });
            jPanel.add(jButton);

            jFrame.setVisible(true);


        }
    }


    /**
     * �ϴ��ļ�֮��Ҫ��List���и���
     */
    private void updateArchiveList() {
        try {
            archiveList = archiveService.getAllArchives();
            int size = archiveList.size();
            for (int i = 0; i < size; i++) {
                archiveInfo[i][0] = archiveList.get(i).getId();
                archiveInfo[i][1] = archiveList.get(i).getFileName();
                archiveInfo[i][2] = archiveList.get(i).getUser().getName();
                archiveInfo[i][3] = archiveList.get(i).getTimestamp();
            }
            archiveScrollTable.updateUI();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
