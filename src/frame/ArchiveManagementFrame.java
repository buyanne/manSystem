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
 * @ClassName : ArchiveManagementFrame //类名
 * @Author : 卟言呢
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
        columNames = new Object[]{"序列号", "文件名", "上传者", "上传时间"};
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
     * 初始化方法
     */
    private void Init() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(600, 400);
        setTitle("案宗管理");
        setResizable(true);
        setLayout(null);
        container = getContentPane();

        // 设置位置为屏幕中央
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);


        //显示文件
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


        //设置滚动将得到的table加入到ScrollTable中
        archiveScrollTable = new JScrollPane(archiveTable);
        archiveScrollTable.setSize(500, 150);
        archiveScrollTable.setLocation(40, 30);
        container.add(archiveScrollTable);


        //下载按钮设置
        downloadButton = new JButton("下载");
        downloadButton.setToolTipText("下载选中的案宗到指定位置");
        downloadButton.setBounds(100, 220, 100, 20);
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadButtonAction(e);
            }
        });
        container.add(downloadButton);

        //上传案宗文件设置
        uploadButton = new JButton("上传案宗");
        uploadButton.setToolTipText("从本地文件中上传");
        uploadButton.setBounds(320, 220, 100, 20);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadButtonAction(e);
            }
        });
        container.add(uploadButton);


        deleteButton = new JButton("删除案宗");
        deleteButton.setToolTipText("删除选中的案宗");
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
            JOptionPane.showMessageDialog(this, "没有选中");
        } else {
            long id = selectedRow;

            int dialog = JOptionPane.showConfirmDialog(this, "确定删除吗？", "删除提示", JOptionPane.OK_CANCEL_OPTION);
            if (dialog == JOptionPane.OK_OPTION) {
                try {
                    archiveService.deleteArchive(id);
                    JOptionPane.showMessageDialog(this, "删除成功");
                    updateArchiveList();
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 点击下载按钮的响应
     * 如果没有选中文件，则弹窗提示没有选择文件
     * 选中了则进入下载路径的选择
     * 如果没选择下载路径则下载到默认位置
     *
     * @param event
     */
    private void downloadButtonAction(ActionEvent event) {

        int selectedRow = archiveTable.getSelectedRow() + 1;
        if (selectedRow > archiveList.size()) {
            JOptionPane.showMessageDialog(this, "没有选中文件");
        } else {
            JFileChooser fileChooser = new JFileChooser();
            //设置可选范围
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showSaveDialog(this);
            //如果选择后点击确认，则继续获得路径并下载
            if (result == JFileChooser.APPROVE_OPTION) {
                //获取选中的路径
                String targetPath = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    archiveService.downloadArchive(selectedRow, targetPath);
                    JOptionPane.showMessageDialog(this, "下载成功");
                } catch (BaseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //打开文件选择框之后没有选择位置，则下载到默认位置
                int flag = JOptionPane.showConfirmDialog(this, "未选下载位置\n将下载到默认位置");
                String targetPath = Constants.DOWNLOAD_PATH;
                if (flag == JOptionPane.OK_OPTION) {
                    try {
                        archiveService.downloadArchive(selectedRow, targetPath);
                        JOptionPane.showMessageDialog(this, "下载成功");
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
     * 上传文件按钮响应
     *
     * @param event
     */
    private void uploadButtonAction(ActionEvent event) {
        JFileChooser jFileChooser = new JFileChooser();
        //设置只可选择文件
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //设置默认为当前目录
        jFileChooser.setCurrentDirectory(new File("."));
        //判断是否选择了文件
        int flag = jFileChooser.showOpenDialog(this);
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获取选择的文件
            File file = jFileChooser.getSelectedFile();
            //设置default属性
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


            //选择security等级
            JFrame jFrame = new JFrame("选择security等级");
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

            JButton jButton = new JButton("确定");
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
                        JOptionPane.showMessageDialog(null, "文件" + file.getName() + "上传成功");
                        //更新archiveList
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
     * 上传文件之后要对List进行更新
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
