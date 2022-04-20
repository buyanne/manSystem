package service.Impl;

import com.mysql.cj.exceptions.PasswordExpiredException;
import dao.UserDao;
import entityClass.User;
import exception.BaseException;
import exception.NoDaoException;
import exception.NoUserException;
import service.UserService;

import java.io.Serializable;
import java.util.List;

/**
 * @author ß²ÑÔÄØ
 */
public class UserServiceImpl implements UserService {

    transient private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User Login(String name, String password) {
        try {
            User user = userDao.findByName(new User() {{
                setName(name);
                setPassword(password);
            }});
            if (user == null) {
                throw new NoUserException();
            } else if (!user.getPassword().equals(password)) {
                throw new PasswordExpiredException();
            }
            return user;
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        try {
            userDao.insert(user);
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void removeUser(User user) {
        try {
            userDao.delete(user);
        } catch (BaseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getUsers() {
        try {
            if (userDao != null) {
                return userDao.findAllOnes();
            } else {
                throw new NoDaoException();
            }

        } catch (BaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(User user) {
        try {
            return userDao.findByName(user);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
