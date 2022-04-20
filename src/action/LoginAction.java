package action;

import application.Application;
import dao.Impl.UserDaoContainer;
import entityClass.User;
import exception.NoUserException;

/**
 * @author ߲����
 */
public class LoginAction extends BaseAction {
    public static final String message = "��¼";

    private String name;
    private String password;


    @Override
    public void input() {
        System.out.println(getText());
        System.out.println("�������û���");
        name = sc.next();
        System.out.println("���������");
        password = sc.next();

    }

    /**
     * @reurn User
     * @Param :nul
     * @Desciption : ��ʼ��currentUser
     */
    public User login() {
        User user = null;

        try {
            input();
            if ((user = UserDaoContainer.search(name, password)) != null) {
                Application.currentUser = user;
            } else {
                throw new NoUserException();
            }
        } catch (NoUserException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void execute() {
        login();
    }

    @Override
    public String getText() {
        return message;
    }
}
