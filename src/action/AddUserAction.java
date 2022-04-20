package action;

import entityClass.Administrator;
import entityClass.Browser;
import entityClass.Operator;
import entityClass.User;

import java.util.Locale;

public class AddUserAction extends BaseAction {
    public static final String message = "����û�";
    User user;

    @Override
    public void input() {
        System.out.println("�������û���");
        String name = sc.nextLine();
        System.out.println("���������");
        String password = sc.nextLine();
        System.out.println("���������");
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
        }
    }

    @Override
    public void execute() {
        System.out.println(message);
        input();
        userService.createUser(user);
        System.out.println(user.getName() + "��ӳɹ�");
    }

    @Override
    public String getText() {
        return message;
    }
}
