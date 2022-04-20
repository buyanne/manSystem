package controller;

import entityClass.User;

/**
 * @ClassName : RemoveUserController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class RemoveUserController extends BaseController {
    @Override
    public void service() {
        User user = (User) message.getParameter("user");
        try {
            userService.removeUser(user);
            message.setData(user);
            onSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
