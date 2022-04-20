package controller;

import entityClass.User;
import exception.BaseException;

/**
 * @ClassName : LoginController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class LoginController extends BaseController {
    @Override
    public void service() {
        String userName = (String) message.getParameter("userName");
        String password = (String) message.getParameter("password");
        User user = null;
        try {
            user = new User();
            user.setName(userName);
            user.setPassword(password);

            user = userService.getUser(user);
            message.setData(user);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
