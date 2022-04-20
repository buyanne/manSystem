package service.ClientServiceImpl;

import common.Constants;
import dao.UserDao;
import entityClass.Message;
import entityClass.User;
import exception.BaseException;
import exception.ServerException;
import service.UserService;

import java.util.List;

/**
 * @ClassName : UserClientService //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/20
 */
public class UserClientService extends BaseClientService implements UserService {
    transient private UserDao userDao = null;

    @Override
    public User getUser(User user) throws BaseException {
        Message message = new Message();
        message.setController(Constants.GET_USER_CONTROLLER);
        message.setParameter("userName", user.getName());
        message = send(message);
        User usertemp = (User) message.getData();
        return usertemp;
    }

    @Override
    public List<User> getUsers() throws BaseException {
        Message message = new Message();
        message.setController(Constants.GET_ALL_USERS_CONTROLLER);
        message = send(message);
        List<User> users = (List<User>) message.getData();
        return users;
    }

    @Override
    public void removeUser(User user) throws BaseException {
        Message message = new Message();
        message.setController(Constants.REMOVE_USER_CONTROLLER);
        message.setParameter("user", user);
        send(message);
    }

    @Override
    public void updateUser(User user) throws BaseException {
        Message message = new Message();
        message.setController(Constants.UPDATE_USER_CONTROLLER);
        message.setParameter("user", user);
        send(message);
    }

    @Override
    public void createUser(User user) throws BaseException {
        Message message = new Message();
        message.setController(Constants.ADD_USER_CONTROLLER);
        message.setParameter("user", user);
        send(message);
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User Login(String name, String password) {
        Message message = new Message();
        message.setController(Constants.LOGIN_CONTROLLER);
        message.setParameter("userName", name);
        message.setParameter("password", password);
        message = send(message);
        return (User) message.getData();

    }
}
