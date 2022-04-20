package action;

import dao.Impl.UserDaoContainer;
import entityClass.User;
import exception.NoUserException;

public class RemoveUserAction extends BaseAction {
    public static final String message = "删除用户";

    private String name;

    @Override
    public void input() {
        System.out.println("请输入用户");
        name = sc.nextLine();
    }

    @Override
    public void execute() {
        System.out.println(message);
        input();
        User user = UserDaoContainer.searchUser(name);
        if (user == null) {
            System.out.println("没有该用户");
            try {
                throw new NoUserException();
            } catch (NoUserException e) {
                e.printStackTrace();
            }
        } else {
            userService.removeUser(user);
            System.out.println(user.getName() + " 删除成功");
        }
    }

    @Override
    public String getText() {
        return message;
    }
}
