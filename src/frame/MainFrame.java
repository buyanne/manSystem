package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ClassName : MainFrame //类名
 * @Author : 卟言呢
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
     * 初始化函数
     * 其中有一个主窗口，一个菜单栏
     * 菜单栏中有三个选项，分别为 userManagement , archiveMangement , infoMangement
     * userManagement 中有三个选项，为 addUser , deleteUser , alterUser
     * archiveManagement 中有两个选项 addArchive , downloadArchive
     */
    private void Init() {
        //设置大小
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
        // 设置位置为屏幕中央
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        meau = new JMenuBar();

        //添加用户管理菜单
        userManagement = new JMenu("用户管理 ");
        addUser = new JMenuItem("添加用户");
        addUser.setName("addUser");
        deleteUser = new JMenuItem("删除用户");
        deleteUser.setName("deleteUser");
        alterUser = new JMenuItem("修改用户");
        alterUser.setName("alterUser");
        userManagement.add(addUser);
        userManagement.add(deleteUser);
        userManagement.add(alterUser);
        //用户管理的Item，添加点击的响应
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

        //添加档案管理菜单
        archiveManagement = new JMenu("档案管理");
        archiveManagement.addSeparator();
        addArchive = new JMenuItem("案宗管理");
        archiveManagement.add(addArchive);
        addArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archiveManagementAction();
            }
        });
        meau.add(archiveManagement);

        //增加信息修改菜单
        infoManagement = new JMenu("信息修改");
        alterUserInfo = new JMenuItem("修改信息");
        alterUserInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterUserInfoAction();
            }
        });
        infoManagement.add(alterUserInfo);
        meau.add(infoManagement);


        setMeauByRole();
        //设置菜单栏
        setJMenuBar(meau);

        setVisible(true);
    }

    /**
     * 根据role的不同来显示可操作的功能
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
     * 显示用户管理界面，并根据点击的具体Item来显示
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
     * 生成用户修改菜单
     */
    private void alterUserInfoAction() {
        if (userInfoFrame == null) {
            userInfoFrame = new UserInfoFrame();
        }
        userInfoFrame.setVisible(true);
    }
}
