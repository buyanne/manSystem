package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName : MainFrame //����
 * @Author : ߲����
 * @Data : 2021/12/4
 */
@SuppressWarnings("all")
public class MainFrame extends BaseFrame {
    private JMenuBar meau;
    private JMenu userManagement, archiveManagement, infoManagement;
    private JMenuItem addArchive, alterUserInfo, addUser, deleteUser, alterUser;
    private JPanel jPanel;
    private UserManagementFrame userManagementFrame;
    private UserInfoFrame userInfoFrame;
    private ArchiveManagementFrame archiveManagementFrame;


    public MainFrame() {
        Init();
    }

    /**
     * ��ʼ������
     * ������һ�������ڣ�һ���˵���
     * �˵�����������ѡ��ֱ�Ϊ userManagement , archiveMangement , infoMangement
     * userManagement ��������ѡ�Ϊ addUser , deleteUser , alterUser
     * archiveManagement ��������ѡ�� addArchive , downloadArchive
     */
    private void Init() {
        //���ô�С
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(user.getName() + " " + user.getRole());


        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        // ����λ��Ϊ��Ļ����
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        meau = new JMenuBar();

        //����û�����˵�
        userManagement = new JMenu("�û����� ");
        addUser = new JMenuItem("����û�");
        addUser.setName("addUser");
        deleteUser = new JMenuItem("ɾ���û�");
        deleteUser.setName("deleteUser");
        alterUser = new JMenuItem("�޸��û�");
        alterUser.setName("alterUser");
        userManagement.add(addUser);
        userManagement.add(deleteUser);
        userManagement.add(alterUser);
        //�û������Item����ӵ������Ӧ
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserActionFrame(e);
            }
        });
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserActionFrame(e);
            }
        });
        alterUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserActionFrame(e);
            }
        });
        meau.add(userManagement);

        //��ӵ�������˵�
        archiveManagement = new JMenu("��������");
        archiveManagement.addSeparator();
        addArchive = new JMenuItem("���ڹ���");
        archiveManagement.add(addArchive);
        addArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archiveManagementAction();
            }
        });
        meau.add(archiveManagement);

        //������Ϣ�޸Ĳ˵�
        infoManagement = new JMenu("��Ϣ�޸�");
        alterUserInfo = new JMenuItem("�޸���Ϣ");
        alterUserInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterUserInfoAction();
            }
        });
        infoManagement.add(alterUserInfo);
        meau.add(infoManagement);


        setMeauByRole();
        //���ò˵���
        setJMenuBar(meau);

        setVisible(true);
    }

    /**
     * ����role�Ĳ�ͬ����ʾ�ɲ����Ĺ���
     */
    private void setMeauByRole() {
        if ("operator".equals(user.getRole())) {
            userManagement.setEnabled(false);
        } else if ("browser".equals(user.getRole())) {
            userManagement.setEnabled(false);
        } else if ("administrator".equals(user.getRole())) {

        }
    }


    private void archiveManagementAction() {
        if (archiveManagementFrame == null) {
            archiveManagementFrame = new ArchiveManagementFrame();
            archiveManagementFrame.setFrameByRole();
        }
        archiveManagementFrame.setVisible(true);
    }

    /**
     * ��ʾ�û�������棬�����ݵ���ľ���Item����ʾ
     *
     * @param event
     */
    private void addUserActionFrame(ActionEvent event) {
        if (userManagementFrame == null) {
            userManagementFrame = new UserManagementFrame();
        }
        JMenuItem source = (JMenuItem) event.getSource();
        userManagementFrame.setTableByName(source.getName());
        userManagementFrame.setVisible(true);
    }

    /**
     * �����û��޸Ĳ˵�
     */
    private void alterUserInfoAction() {
        if (userInfoFrame == null) {
            userInfoFrame = new UserInfoFrame();
        }
        userInfoFrame.setVisible(true);
    }
}
