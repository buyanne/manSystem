package action;

import dao.Impl.UserDaoContainer;
import entityClass.User;
import exception.NoUserException;

public class RemoveUserAction extends BaseAction {
    public static final String message = "ɾ���û�";

    private String name;

    @Override
    public void input() {
        System.out.println("�������û�");
        name = sc.nextLine();
    }

    @Override
    public void execute() {
        System.out.println(message);
        input();
        User user = UserDaoContainer.searchUser(name);
        if (user == null) {
            System.out.println("û�и��û�");
            try {
                throw new NoUserException();
            } catch (NoUserException e) {
                e.printStackTrace();
            }
        } else {
            userService.removeUser(user);
            System.out.println(user.getName() + " ɾ���ɹ�");
        }
    }

    @Override
    public String getText() {
        return message;
    }
}
