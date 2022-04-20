package controller;

import entityClass.User;
import exception.BaseException;

/**
 * @ClassName : GetUserController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class GetUserController extends BaseController {
    @Override
    public void service() {
        String userName=(String)message.getParameter("userName");
        User user=null;
        try{
            user=userService.getUser(user);
            message.setData(user);
            onSuccess();
        }catch(BaseException e){
            e.printStackTrace();
        }
    }
}
