package controller;

import entityClass.User;
import exception.BaseException;

/**
 * @ClassName : ChangePasswordController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class ChangePasswordController extends BaseController {
    @Override
    public void service() {
        String username = (String) message.getParameter("username");
        String newPassword = (String) message.getParameter("newPassword");
        String role = (String) message.getParameter("role");
        User user = new User();
        user.setName(username);
        user.setPassword(newPassword);
        user.setRole(role);
        try {
            user = userService.getUser(user);
            userService.updateUser(user);
            message.setData(user);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
