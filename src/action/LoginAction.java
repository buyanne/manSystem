package action;

import application.Application;
import dao.Impl.UserDaoContainer;
import entityClass.User;
import exception.NoUserException;

/**
 * @author 卟言呢
 */
public class LoginAction extends BaseAction {
    public static final String message = "登录";

    private String name;
    private String password;


    @Override
    public void input() {
        System.out.println(getText());
        System.out.println("请输入用户名");
        name = sc.next();
        System.out.println("请输入口令");
        password = sc.next();

    }

    /**
     * @reurn User
     * @Param :nul
     * @Desciption : 初始化currentUser
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
