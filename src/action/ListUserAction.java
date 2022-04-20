package action;

import entityClass.User;

import java.util.List;

public class ListUserAction extends BaseAction {
    public static final String message = "列出用户";

    @Override
    public void input() {

    }

    @Override
    public void execute() {
        System.out.println(message);
        List<User> list = userService.getUsers();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "--" + list.get(i));
        }
    }

    @Override
    public String getText() {
        return message;
    }
}
