package service;

import dao.UserDao;
import entityClass.User;
import exception.BaseException;

import java.util.List;

public interface UserService {
    List<User> getUsers() throws BaseException;

    void removeUser(User user) throws BaseException;

    void updateUser(User user) throws BaseException;

    void createUser(User user) throws BaseException;

    User getUser(User user)throws BaseException;

    void setUserDao(UserDao userDao);

    User Login(String name, String password);
}
