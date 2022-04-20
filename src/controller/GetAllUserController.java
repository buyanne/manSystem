package controller;

import entityClass.User;
import exception.BaseException;

import java.util.List;

/**
 * @ClassName : GetAllUserController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class GetAllUserController extends BaseController {
    @Override
    public void service() {
        List<User> list = null;
        try {
            list = userService.getUsers();
            message.setData(list);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }

    }
}
