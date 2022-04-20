package dao.Impl;

import entityClass.Administrator;
import entityClass.Browser;
import entityClass.Operator;
import entityClass.User;
import exception.BaseException;
import utils.JdbcUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName : UserDaoJdbc //ÀàÃû
 * @Author : ß²ÑÔÄØ
 * @Data : 2021/12/9
 */
public class UserDaoJdbc extends UserDaoContainer {
    public UserDaoJdbc() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from user_info";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery(sql);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    User user = null;
                    if ("operator".equals(role)) {
                        user = new Operator();
                    } else if ("browser".equals(role)) {
                        user = new Browser();

                    } else if ("administrator".equals(role)) {
                        user = new Administrator();

                    }
                    if (user != null) {
                        user.setName(name);
                        user.setPassword(password);
                        user.setRole(role);
                        super.insert(user);
                    }
                }
            }
        } catch (SQLException | BaseException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.release(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User insert(User user) throws BaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (super.findByName(new User() {{
            setName(user.getName());
        }}) != null) {
            return null;
        }
        try {
            connection = JdbcUtil.getConnection();
            String sql = "insert into user_info(`name`,`password`,`role`)values(?,?,?)";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());

                int num = preparedStatement.executeUpdate();

                if (num > 0) {
                    return super.insert(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User update(User user) throws BaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "update user_info set password=? where name =?";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());

                int num = preparedStatement.executeUpdate();
                if (num > 0) {
                    return super.update(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.release(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User delete(User user) throws BaseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "delete from user_info where name=?";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getName());

                int num = preparedStatement.executeUpdate();
                if (num > 0) {
                    return super.delete(user);
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.release(connection, preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public List<User> findAllOnes() throws BaseException {
        return super.findAllOnes();
    }
}
