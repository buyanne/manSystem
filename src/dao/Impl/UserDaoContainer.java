package dao.Impl;

import dao.UserDao;
import entityClass.User;
import exception.BaseException;
import exception.NoUserException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @ClassName : UserDao //类名
 * @Description : 控制台的dao类
 * @Author : 卟言呢
 * @Data : 2021/11/20
 */
public class UserDaoContainer implements UserDao {
    private static final Hashtable<String, User> USERS = new Hashtable<>();

    @Override
    public User insert(User user) throws BaseException {
        return USERS.put(user.getName(), user);
    }

    @Override
    public User update(User user) throws BaseException {
        return USERS.put(user.getName(), user);

    }

    @Override
    public User delete(User user) throws BaseException {
        return USERS.remove(user.getName());
    }

    @Override
    public User findByName(User user) throws NoUserException {
        return USERS.get(user.getName());
    }

    @Override
    public List<User> findAllOnes() throws BaseException {
        return new ArrayList<>(USERS.values());
    }

    public static User search(String name, String password) {
        if (USERS.containsKey(name)) {
            User user = USERS.get(name);
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public static User searchUser(String name) {
        if (USERS.containsKey(name)) {
            return USERS.get(name);
        }
        return null;
    }
}
