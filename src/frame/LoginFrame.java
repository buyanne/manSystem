package frame;

import application.Application;
import application.ClientApplication;
import entityClass.User;
import exception.BaseException;
import exception.ServerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @ClassName : mainFrame //����
 * @Author : ߲����
 * @Data : 2021/12/3
 */
@SuppressWarnings("all")
public class LoginFrame extends BaseFrame {

    private Container container;
    private JLabel nameLabel;
    private JTextField userName;
    private JLabel passwordJLabel;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;

    public LoginFrame() {
        Init();
    }

    private void Init() {
        container = this.getContentPane();

        //��ʼ��frame��С
        setSize(300, 200);
        //���ɸ��Ĵ�С
        setResizable(false);
        //�ղ���
        setLayout(null);
        //���õ���ر��¼�
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //����title
        setTitle("��¼");

        //��õ�ǰ��Ļ�Ĵ�С��Ϊ���ý�����ʾ����Ļ������
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        //����frame��λ��
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);


        nameLabel = new JLabel("�û���");
        nameLabel.setSize(50, 20);
        nameLabel.setLocation(60, 30);
        container.add(nameLabel);

        userName = new JTextField(20);
        userName.setSize(100, 20);
        userName.setLocation(130, 30);
        container.add(userName);

        passwordJLabel = new JLabel("����");
        passwordJLabel.setSize(50, 20);
        passwordJLabel.setLocation(60, 60);
        container.add(passwordJLabel);

        passwordField = new JPasswordField(20);
        passwordField.setSize(100, 20);
        passwordField.setLocation(130, 60);
        container.add(passwordField);

        okButton = new JButton("ȷ��");
        okButton.setLocation(60, 100);
        okButton.setSize(80, 20);
        //��������¼�
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonPressedAction();
            }
        });

        //���ϼ���Enter����ȷ����ť��
        okButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                okButtonPressedAction();
            }
        });
        container.add(okButton);


        cancelButton = new JButton("ȡ��");
        cancelButton.setLocation(150, 100);
        cancelButton.setFocusPainted(false);
        cancelButton.setSize(80, 20);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        cancelButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cancelButtonAction();
            }
        });
        container.add(cancelButton);

        setVisible(true);

    }


    /**
     * ���ȷ����ť��Ӧ
     * ������ȷ��ô������䣬ͬʱ����һ�����˵�
     * ���򵯴���ʾ�������
     */
    private void okButtonPressedAction() {
        String name = userName.getText();
        String password = new String(passwordField.getPassword());


        User user = null;

        user = userService.Login(name, password);
        if (user != null) {
            ClientApplication.currentUser = user;
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "�û�����������Ϊ��", "������ʾ", JOptionPane.PLAIN_MESSAGE);
        }



        /*User userTemp = null;
        if ((userTemp = UserDaoContainer.search(name, password)) != null) {
            Application.currentUser = userTemp;
            this.user = userTemp;
            this.setVisible(false);
            new MainFrame();

        } else {
            JOptionPane.showMessageDialog(null,
                    "�û�������������Ϊ��",
                    "������ʾ",
                    JOptionPane.PLAIN_MESSAGE);
        }*/
    }

    /**
     * ȡ����ť��Ӧ
     * ���û������������Ϊ��
     */
    private void cancelButtonAction() {
        userName.setText("");
        passwordField.setText("");
    }

}
