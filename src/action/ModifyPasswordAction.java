package action;

import application.Application;
import entityClass.User;

public class ModifyPasswordAction extends BaseAction {
    public static final String message = "修改密码";
    private String password;

    @Override
    public void input() {
        System.out.println("请输入新口令");
        password = sc.nextLine();
    }

    @Override
    public void execute() {
        input();
        Application.currentUser.setPassword(password);
        userService.updateUser(Application.currentUser);
        sc.nextLine();
    }

    @Override
    public String getText() {
        return message;
    }
}
