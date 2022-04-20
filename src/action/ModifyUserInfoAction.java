package action;

import dao.Impl.UserDaoContainer;
import entityClass.Administrator;
import entityClass.Browser;
import entityClass.Operator;
import entityClass.User;
import exception.NoUserException;

import java.util.Locale;

public class ModifyUserInfoAction extends BaseAction {
    public static final String message = "修改信息";

    private User user;

    @Override
    public void input() {
        System.out.println("请输入用户名");
        String name = sc.nextLine();
        try {
            user = UserDaoContainer.searchUser(name);
            if (user == null) {
                throw new NoUserException();
            }
            System.out.println("请输入新口令");
            String password = sc.nextLine();
            System.out.println("请输入新身份");
            String role = sc.nextLine().toLowerCase(Locale.ROOT);
            switch (role) {
                case "browser":
                    user = new Browser(name, password, role);
                    break;
                case "operator":
                    user = new Operator(name, password, role);
                    break;
                case "administrator":
                    user = new Administrator(name, password, role);
                    break;
                default:
                    break;
            }

        } catch (NoUserException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void execute() {
        input();
        userService.updateUser(user);
        System.out.println("修改成功"   );

    }

    @Override
    public String getText() {
        return message;
    }
}
