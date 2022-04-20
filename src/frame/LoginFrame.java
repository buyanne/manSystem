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
 * @ClassName : mainFrame //类名
 * @Author : 卟言呢
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

        //初始化frame大小
        setSize(300, 200);
        //不可更改大小
        setResizable(false);
        //空布局
        setLayout(null);
        //设置点击关闭事件
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置title
        setTitle("登录");

        //获得当前屏幕的大小，为了让界面显示在屏幕的中央
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        //设置frame的位置
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);


        nameLabel = new JLabel("用户名");
        nameLabel.setSize(50, 20);
        nameLabel.setLocation(60, 30);
        container.add(nameLabel);

        userName = new JTextField(20);
        userName.setSize(100, 20);
        userName.setLocation(130, 30);
        container.add(userName);

        passwordJLabel = new JLabel("密码");
        passwordJLabel.setSize(50, 20);
        passwordJLabel.setLocation(60, 60);
        container.add(passwordJLabel);

        passwordField = new JPasswordField(20);
        passwordField.setSize(100, 20);
        passwordField.setLocation(130, 60);
        container.add(passwordField);

        okButton = new JButton("确定");
        okButton.setLocation(60, 100);
        okButton.setSize(80, 20);
        //监听点击事件
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonPressedAction();
            }
        });

        //加上键盘Enter触发确定按钮键
        okButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                okButtonPressedAction();
            }
        });
        container.add(okButton);


        cancelButton = new JButton("取消");
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
     * 点击确定按钮响应
     * 输入正确则该窗口隐匿，同时创建一个主菜单
     * 否则弹窗提示输入错误
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
            JOptionPane.showMessageDialog(null, "用户名密码错误或为空", "错误提示", JOptionPane.PLAIN_MESSAGE);
        }



        /*User userTemp = null;
        if ((userTemp = UserDaoContainer.search(name, password)) != null) {
            Application.currentUser = userTemp;
            this.user = userTemp;
            this.setVisible(false);
            new MainFrame();

        } else {
            JOptionPane.showMessageDialog(null,
                    "用户名或密码错误或为空",
                    "错误提示",
                    JOptionPane.PLAIN_MESSAGE);
        }*/
    }

    /**
     * 取消按钮响应
     * 设用户名域和密码域为空
     */
    private void cancelButtonAction() {
        userName.setText("");
        passwordField.setText("");
    }

}
