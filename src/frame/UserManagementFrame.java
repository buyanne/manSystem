package frame;

import dao.Impl.UserDaoContainer;
import entityClass.Administrator;
import entityClass.Browser;
import entityClass.Operator;
import entityClass.User;
import exception.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @ClassName : UserManagementFrame //类名
 * @Author : 卟言呢
 * @Data : 2021/12/4
 */
@SuppressWarnings("all")
public class UserManagementFrame extends BaseFrame {
    private Container container;
    private JTabbedPane table;
    private JPanel addUser, deleteUser, alterUser;
    private JLabel userNameLabelAdd, userNameLabelAlter,
            passwordLabel, passwordLabelAlter,
            roleLabelAlter, roleLabelAdd;
    private JTextField userNameField;
    private JPasswordField passwordFieldAdd, passwordFieldAlter;
    private JComboBox<String> roleBoxAdd, roleBoxAlter, userBoxAlter, userBoxDelete;
    private JButton addUserButton, cancelButton, alterButton, deleteButton;
    private List<User> userListDelete, userListAlter;

    public UserManagementFrame() {
        Init();
    }


    /**
     * 初始化界面方法
     */
    private void Init() {
        container = getContentPane();
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);

        // 设置位置为屏幕中央
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        //table存储三个Panel界面
        table = new JTabbedPane();
        container.add(table, BorderLayout.CENTER);

        //添加用户的面板
        addUser = new JPanel();
        addUser.setName("增加用户");
        //绝对布局
        addUser.setLayout(null);
        table.addTab(addUser.getName(), null, addUser, null);

        //增加组件到添加用户面板
        userNameLabelAdd = new JLabel("用户名");
        userNameLabelAdd.setBounds(100, 60, 60, 15);
        addUser.add(userNameLabelAdd);

        userNameField = new JTextField();
        userNameField.setBounds(200, 60, 130, 20);
        addUser.add(userNameField);

        passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(100, 150, 60, 15);
        addUser.add(passwordLabel);

        passwordFieldAdd = new JPasswordField();
        passwordFieldAdd.setBounds(200, 150, 130, 20);
        addUser.add(passwordFieldAdd);


        roleLabelAdd = new JLabel("角色");
        roleLabelAdd.setBounds(100, 250, 60, 15);
        addUser.add(roleLabelAdd);

        roleBoxAdd = new JComboBox<>();
        roleBoxAdd.setBounds(200, 250, 130, 20);
        roleBoxAdd.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "administrator",
                        "operator",
                        "browser"
                }));
        addUser.add(roleBoxAdd);

        addUserButton = new JButton("添加");
        addUserButton.setBounds(150, 330, 60, 20);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserButtonAction(e);
            }
        });
        addUser.add(addUserButton);

        cancelButton = new JButton("撤销");
        cancelButton.setBounds(250, 330, 60, 20);
        addUser.add(cancelButton);


        //删除用户面板
        deleteUser = new JPanel();
        deleteUser.setName("删除用户");
        deleteUser.setLayout(null);
        table.addTab(deleteUser.getName(), null, deleteUser, null);

        userBoxDelete = new JComboBox<>();
        userBoxDelete.setBounds(170, 60, 160, 30);
        try {
            userListDelete = userService.getUsers();
            String[] names = new String[userListDelete.size()];
            for (int i = 0; i < names.length; i++) {
                names[i] = userListDelete.get(i).getName();
            }
            userBoxDelete.setModel(new DefaultComboBoxModel<>(names));
        } catch (BaseException e) {
            e.printStackTrace();
        }
        deleteUser.add(userBoxDelete);

        deleteButton = new JButton("确认删除");
        deleteButton.setBounds(120, 240, 260, 40);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonAction(e);
            }
        });
        deleteUser.add(deleteButton);


        //修改用户信息面板
        alterUser = new JPanel();
        alterUser.setName("修改用户信息");
        alterUser.setLayout(null);
        table.addTab(alterUser.getName(), null, alterUser, null);

        userNameLabelAlter = new JLabel("用户");
        userNameLabelAlter.setBounds(100, 60, 60, 15);
        alterUser.add(userNameLabelAlter);

        userBoxAlter = new JComboBox<>();
        userBoxAlter.setBounds(200, 60, 130, 20);
        try {
            userListAlter = userService.getUsers();
            String[] names = new String[userListAlter.size()];
            for (int i = 0; i < userListAlter.size(); i++) {
                names[i] = userListAlter.get(i).getName();
            }
            userBoxAlter.setModel(new DefaultComboBoxModel<>(names));
            alterUser.add(userBoxAlter);
        } catch (BaseException e) {
            e.printStackTrace();
        }


        passwordLabelAlter = new JLabel("密码");
        passwordLabelAlter.setBounds(100, 150, 60, 15);
        alterUser.add(passwordLabelAlter);

        passwordFieldAlter = new JPasswordField();
        passwordFieldAlter.setBounds(200, 150, 130, 20);
        alterUser.add(passwordFieldAlter);


        roleLabelAlter = new JLabel("角色");
        roleLabelAlter.setBounds(100, 250, 60, 15);
        alterUser.add(roleLabelAlter);


        roleBoxAlter = new JComboBox<>();
        roleBoxAlter.setBounds(200, 250, 130, 20);
        roleBoxAlter.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "administrator",
                        "operator",
                        "browser"
                }));
        alterUser.add(roleBoxAlter);

        alterButton = new JButton("确认修改");
        alterButton.setBounds(200, 330, 130, 20);
        alterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterButtonAction(e);
            }
        });
        alterUser.add(alterButton);


        setVisible(true);
    }

    /**
     * 添加用户按钮Action
     * 用户名或密码为空，提示有误
     * 否则继续判断，若已有该用户则返回
     * 若无则创建对应的对象，再添加到容器中，并弹窗提示保存成功
     *
     * @param event
     */
    public void addUserButtonAction(ActionEvent event) {
        String name = userNameField.getText();
        String password = new String(passwordFieldAdd.getPassword());
        String role = (String) roleBoxAdd.getSelectedItem();
        userNameField.setText("");
        passwordFieldAdd.setText("");

        User userTemp = null;

        if ("".equals(name) || "".equals(password)) {
            //如果用户名或密码为空则弹窗提示
            JOptionPane.showMessageDialog(this, "用户名或密码为空", "输入提示", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                //如果已有该用户名
                if (UserDaoContainer.searchUser(name) != null) {
                    JOptionPane.showMessageDialog(this, name + "用户已存在", "重复添加", JOptionPane.PLAIN_MESSAGE);
                } else {
                    //未存在则继续保存
                    //根据选择的role创建相应的User子类
                    switch (role) {
                        case "browser":
                            userTemp = new Browser();
                            break;
                        case "operator":
                            userTemp = new Operator();
                        case "administrator":
                            userTemp = new Administrator();
                        default:
                            break;
                    }
                    userTemp.setName(name);
                    userTemp.setPassword(password);
                    userTemp.setRole(role);
                    //保存到数据容器中
                    userService.createUser(userTemp);
                    //弹窗提示用户添加成功
                    JOptionPane.showMessageDialog(this, name + "用户添加成功", "成功", JOptionPane.PLAIN_MESSAGE);
                    updateInfo();
                }
            } catch (BaseException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 删除用户的确定按钮Action
     * 点击删除按钮后，确定弹窗提示
     * 若继续确定，则删除该用户，并弹窗提示删除成功
     *
     * @param event
     */
    public void deleteButtonAction(ActionEvent event) {
        String name = (String) userBoxDelete.getSelectedItem();
        User user = new User();
        user.setName(name);
        int flag = JOptionPane.showConfirmDialog(this, "确认删除" + name + "用户吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
        if (flag == JOptionPane.OK_OPTION) {
            if (this.user.getName()!=user.getName()) {
                try {
                    userService.removeUser(user);
                    JOptionPane.showMessageDialog(this, "用户" + name + "删除成功");
                    updateInfo();
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "不能删除当前用户");
            }
        }
    }

    /**
     * 点击修改用户按钮的Action
     * 如果为空则空输入弹窗提示
     * 否则继续继续弹窗，确定窗口
     * 如果为是，则修改用户信息，否则返回
     *
     * @param e
     */
    public void alterButtonAction(ActionEvent e) {
        String password = new String(passwordFieldAlter.getPassword());
        passwordFieldAlter.setText("");
        String name = (String) userBoxAlter.getSelectedItem();
        String role = (String) roleBoxAlter.getSelectedItem();
        int flag = 0;
        if (!"".equals(password)) {
            //记录是否点确定按钮
            flag = JOptionPane.showConfirmDialog(this, "确定修改吗？", "确定提示", JOptionPane.OK_CANCEL_OPTION);
            //如果点的确定按钮就就更新对象
            if (flag == JOptionPane.OK_OPTION) {
                //根据role生成相应的对象
                User userTemp = null;
                if ("browser".equals(role)) {
                    userTemp = new Browser();
                } else if ("operator".equals(role)) {
                    userTemp = new Operator();

                } else if ("administrator".equals(role)) {
                    userTemp = new Administrator();
                }
                userTemp.setName(name);
                userTemp.setRole(role);
                userTemp.setPassword(password);
                try {
                    userService.updateUser(userTemp);
                    //修改成功则弹窗提示
                    JOptionPane.showMessageDialog(this, name + " 密码修改成功 ");
                    updateInfo();
                } catch (BaseException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    /**
     * 由调用者传入的 name 来确定具体显示的table
     *
     * @param name
     */
    public void setTableByName(String name) {
        if ("addUser".equals(name)) {
            table.setSelectedIndex(0);
        } else if ("deleteUser".equals(name)) {
            table.setSelectedIndex(1);
        } else if ("alterUser".equals(name)) {
            table.setSelectedIndex(2);
        }
    }

    /**
     * 进行操作之后要更新信息
     */
    private void updateInfo() {
        try {
            userListAlter = userService.getUsers();
            String[] names = new String[userListAlter.size()];
            for (int i = 0; i < userListAlter.size(); i++) {
                names[i] = userListAlter.get(i).getName();
            }
            userBoxAlter.setModel(new DefaultComboBoxModel<>(names));
        } catch (BaseException e) {
            e.printStackTrace();
        }
        try {
            userListDelete = userService.getUsers();
            String[] names = new String[userListDelete.size()];
            for (int i = 0; i < names.length; i++) {
                names[i] = userListDelete.get(i).getName();
            }
            userBoxDelete.setModel(new DefaultComboBoxModel<>(names));
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
