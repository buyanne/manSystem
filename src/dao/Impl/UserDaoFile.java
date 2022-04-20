package dao.Impl;

import common.Constants;
import dao.Impl.UserDaoContainer;
import entityClass.User;
import exception.BaseException;

import java.io.*;
import java.util.List;

/**
 * @ClassName : UserDaoFile //类名
 * @Description : 从文件中读取
 * @Author : 卟言呢
 * @Data : 2021/11/23
 */
public class UserDaoFile extends UserDaoContainer {
    public UserDaoFile() {
        ObjectInputStream objectInputStream = null;
        try {
            File file = new File(Constants.USER_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            User user = null;
            while (null != (user = (User) objectInputStream.readObject())) {
                super.insert(user);
            }

        } catch (EOFException ignored) {

        } catch (IOException | BaseException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void Save() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.USER_PATH));
            List<User> list = this.findAllOnes();
            for (User user : list) {
                objectOutputStream.writeObject(user);
            }
        } catch (BaseException | IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public User insert(User user) throws BaseException {
        User user1 = super.insert(user);
        Save();
        return user1;
    }

    @Override
    public User update(User user) throws BaseException {
        User user1 = super.update(user);
        Save();
        return user1;
    }

    @Override
    public User delete(User user) throws BaseException {
        User user1 = super.delete(user);
        Save();
        return user1;
    }
}
