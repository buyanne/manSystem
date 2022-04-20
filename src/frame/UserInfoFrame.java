package frame;

import exception.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @ClassName : UserInfoFrame //类名
 * @Author : 卟言呢
 * @Data : 2021/12/4
 */
public class UserInfoFrame extends BaseFrame {
    private JLabel formerPasswordLabel, latestPasswordLabel;
    private JTextField formerPasswordField;
    private JPasswordField latestPasswordField;
    private Container container;


    public UserInfoFrame() {
        Init();
    }


    /**
     * 初始化界面方法
     */
    public void Init() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(400, 400);
        setTitle("修改信息");
        setLayout(null);
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        // 设置位置为屏幕中央
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        container = getContentPane();

        formerPasswordLabel = new JLabel("原密码");
        formerPasswordLabel.setSize(50, 20);
        formerPasswordLabel.setLocation(100, 100);
        container.add(formerPasswordLabel);

        formerPasswordField = new JTextField(20);
        formerPasswordField.setSize(100, 20);
        formerPasswordField.setLocation(200, 100);
        container.add(formerPasswordField);

        latestPasswordLabel = new JLabel("新密码");
        latestPasswordLabel.setSize(50, 20);
        latestPasswordLabel.setLocation(100, 150);
        container.add(latestPasswordLabel);

        latestPasswordField = new JPasswordField(20);
        latestPasswordField.setSize(100, 20);
        latestPasswordField.setLocation(200, 150);
        container.add(latestPasswordField);

        JButton okButton = new JButton("确定修改");
        okButton.setSize(100, 20);
        okButton.setLocation(150, 230);
        okButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                okButtonPressedAction();
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonPressedAction();
            }
        });
        container.add(okButton);


        setVisible(true);

    }

    /**
     * 点击确定修改按钮Action
     * 与 UserManagementFrame 中 alterButtonAction(ActionEvent e) 作用相同
     */
    private void okButtonPressedAction() {
        String formerPassword = formerPasswordField.getText();
        String latestPassword = new String(latestPasswordField.getPassword());
        //如果密码栏都不为空并且原密码不正确，创建一个弹窗提示原密码错误
        if (!user.getPassword().equals(formerPassword)
                || "".equals(formerPassword)
                || "".equals(latestPassword)) {
            JOptionPane.showMessageDialog(null,
                    "有未填写或输入错误",
                    "错误提示",
                    JOptionPane.PLAIN_MESSAGE);
            formerPasswordField.setText("");
            latestPasswordField.setText("");
        } else if (user.getPassword().equals(formerPassword)) {
            //修改密码数据
            user.setPassword(latestPassword);
            try {
                userService.updateUser(user);
                //弹出窗口显示修改密码成功.
                JOptionPane.showMessageDialog(null,
                        "密码修改成功",
                        "",
                        JOptionPane.PLAIN_MESSAGE);
                formerPasswordField.setText("");
                latestPasswordField.setText("");
                setVisible(false);

            } catch (BaseException e) {
                e.printStackTrace();
            }
        }
    }


}
