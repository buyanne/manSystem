package utils;

import common.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName : JdbcUtils //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/9
 */
public class JdbcUtil {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {

        try {
            driver = Constants.DRIVER;
            url = Constants.URL;
            username = Constants.USERNAME;
            password = Constants.PASSWORD;

            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
