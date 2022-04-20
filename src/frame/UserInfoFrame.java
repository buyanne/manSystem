package frame;

import exception.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @ClassName : UserInfoFrame //����
 * @Author : ߲����
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
     * ��ʼ�����淽��
     */
    public void Init() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(400, 400);
        setTitle("�޸���Ϣ");
        setLayout(null);
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frameHeight = getHeight();
        int frameWidth = getWidth();
        // ����λ��Ϊ��Ļ����
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        container = getContentPane();

        formerPasswordLabel = new JLabel("ԭ����");
        formerPasswordLabel.setSize(50, 20);
        formerPasswordLabel.setLocation(100, 100);
        container.add(formerPasswordLabel);

        formerPasswordField = new JTextField(20);
        formerPasswordField.setSize(100, 20);
        formerPasswordField.setLocation(200, 100);
        container.add(formerPasswordField);

        latestPasswordLabel = new JLabel("������");
        latestPasswordLabel.setSize(50, 20);
        latestPasswordLabel.setLocation(100, 150);
        container.add(latestPasswordLabel);

        latestPasswordField = new JPasswordField(20);
        latestPasswordField.setSize(100, 20);
        latestPasswordField.setLocation(200, 150);
        container.add(latestPasswordField);

        JButton okButton = new JButton("ȷ���޸�");
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
     * ���ȷ���޸İ�ťAction
     * �� UserManagementFrame �� alterButtonAction(ActionEvent e) ������ͬ
     */
    private void okButtonPressedAction() {
        String formerPassword = formerPasswordField.getText();
        String latestPassword = new String(latestPasswordField.getPassword());
        //�������������Ϊ�ղ���ԭ���벻��ȷ������һ��������ʾԭ�������
        if (!user.getPassword().equals(formerPassword)
                || "".equals(formerPassword)
                || "".equals(latestPassword)) {
            JOptionPane.showMessageDialog(null,
                    "��δ��д���������",
                    "������ʾ",
                    JOptionPane.PLAIN_MESSAGE);
            formerPasswordField.setText("");
            latestPasswordField.setText("");
        } else if (user.getPassword().equals(formerPassword)) {
            //�޸���������
            user.setPassword(latestPassword);
            try {
                userService.updateUser(user);
                //����������ʾ�޸�����ɹ�.
                JOptionPane.showMessageDialog(null,
                        "�����޸ĳɹ�",
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
