package controller;

import entityClass.User;
import exception.BaseException;

/**
 * @ClassName : AddUserController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class AddUserController extends BaseController {
    @Override
    public void service(){
        User user=(User)message.getParameter("user");
        try{
            userService.createUser(user);
            message.setData(user);
            onSuccess();
        }catch(BaseException e){
            e.printStackTrace();
        }
    }

}
