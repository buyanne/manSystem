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
 * @ClassName : UserManagementFrame //����
 * @Author : ߲����
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
     * ��ʼ�����淽��
     */
    private void Init() {
        container = getContentPane();
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);

        // ����λ��Ϊ��Ļ����
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        //table�洢����Panel����
        table = new JTabbedPane();
        container.add(table, BorderLayout.CENTER);

        //����û������
        addUser = new JPanel();
        addUser.setName("�����û�");
        //���Բ���
        addUser.setLayout(null);
        table.addTab(addUser.getName(), null, addUser, null);

        //�������������û����
        userNameLabelAdd = new JLabel("�û���");
        userNameLabelAdd.setBounds(100, 60, 60, 15);
        addUser.add(userNameLabelAdd);

        userNameField = new JTextField();
        userNameField.setBounds(200, 60, 130, 20);
        addUser.add(userNameField);

        passwordLabel = new JLabel("����");
        passwordLabel.setBounds(100, 150, 60, 15);
        addUser.add(passwordLabel);

        passwordFieldAdd = new JPasswordField();
        passwordFieldAdd.setBounds(200, 150, 130, 20);
        addUser.add(passwordFieldAdd);


        roleLabelAdd = new JLabel("��ɫ");
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

        addUserButton = new JButton("���");
        addUserButton.setBounds(150, 330, 60, 20);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserButtonAction(e);
            }
        });
        addUser.add(addUserButton);

        cancelButton = new JButton("����");
        cancelButton.setBounds(250, 330, 60, 20);
        addUser.add(cancelButton);


        //ɾ���û����
        deleteUser = new JPanel();
        deleteUser.setName("ɾ���û�");
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

        deleteButton = new JButton("ȷ��ɾ��");
        deleteButton.setBounds(120, 240, 260, 40);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonAction(e);
            }
        });
        deleteUser.add(deleteButton);


        //�޸��û���Ϣ���
        alterUser = new JPanel();
        alterUser.setName("�޸��û���Ϣ");
        alterUser.setLayout(null);
        table.addTab(alterUser.getName(), null, alterUser, null);

        userNameLabelAlter = new JLabel("�û�");
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


        passwordLabelAlter = new JLabel("����");
        passwordLabelAlter.setBounds(100, 150, 60, 15);
        alterUser.add(passwordLabelAlter);

        passwordFieldAlter = new JPasswordField();
        passwordFieldAlter.setBounds(200, 150, 130, 20);
        alterUser.add(passwordFieldAlter);


        roleLabelAlter = new JLabel("��ɫ");
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

        alterButton = new JButton("ȷ���޸�");
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
     * ����û���ťAction
     * �û���������Ϊ�գ���ʾ����
     * ��������жϣ������и��û��򷵻�
     * �����򴴽���Ӧ�Ķ�������ӵ������У���������ʾ����ɹ�
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
            //����û���������Ϊ���򵯴���ʾ
            JOptionPane.showMessageDialog(this, "�û���������Ϊ��", "������ʾ", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {
                //������и��û���
                if (UserDaoContainer.searchUser(name) != null) {
                    JOptionPane.showMessageDialog(this, name + "�û��Ѵ���", "�ظ����", JOptionPane.PLAIN_MESSAGE);
                } else {
                    //δ�������������
                    //����ѡ���role������Ӧ��User����
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
                    //���浽����������
                    userService.createUser(userTemp);
                    //������ʾ�û���ӳɹ�
                    JOptionPane.showMessageDialog(this, name + "�û���ӳɹ�", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
                    updateInfo();
                }
            } catch (BaseException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * ɾ���û���ȷ����ťAction
     * ���ɾ����ť��ȷ��������ʾ
     * ������ȷ������ɾ�����û�����������ʾɾ���ɹ�
     *
     * @param event
     */
    public void deleteButtonAction(ActionEvent event) {
        String name = (String) userBoxDelete.getSelectedItem();
        User user = new User();
        user.setName(name);
        int flag = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��" + name + "�û���", "��ʾ", JOptionPane.OK_CANCEL_OPTION);
        if (flag == JOptionPane.OK_OPTION) {
            if (this.user.getName()!=user.getName()) {
                try {
                    userService.removeUser(user);
                    JOptionPane.showMessageDialog(this, "�û�" + name + "ɾ���ɹ�");
                    updateInfo();
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "����ɾ����ǰ�û�");
            }
        }
    }

    /**
     * ����޸��û���ť��Action
     * ���Ϊ��������뵯����ʾ
     * �����������������ȷ������
     * ���Ϊ�ǣ����޸��û���Ϣ�����򷵻�
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
            //��¼�Ƿ��ȷ����ť
            flag = JOptionPane.showConfirmDialog(this, "ȷ���޸���", "ȷ����ʾ", JOptionPane.OK_CANCEL_OPTION);
            //������ȷ����ť�;͸��¶���
            if (flag == JOptionPane.OK_OPTION) {
                //����role������Ӧ�Ķ���
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
                    //�޸ĳɹ��򵯴���ʾ
                    JOptionPane.showMessageDialog(this, name + " �����޸ĳɹ� ");
                    updateInfo();
                } catch (BaseException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    /**
     * �ɵ����ߴ���� name ��ȷ��������ʾ��table
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
     * ���в���֮��Ҫ������Ϣ
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
