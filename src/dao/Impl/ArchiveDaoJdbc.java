package dao.Impl;

import entityClass.Archive;
import entityClass.SecurityClassification;
import entityClass.User;
import exception.BaseException;
import utils.JdbcUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : ArchiveDaoJdbc //ÀàÃû
 * @Author : ß²ÑÔÄØ
 * @Data : 2021/12/9
 */
public class ArchiveDaoJdbc extends ArchiveDaoContainer {


    public ArchiveDaoJdbc() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();

            String sql = "select * from archive_info";
            if (connection != null) {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String fileName = resultSet.getString("fileName");
                    String keyword = resultSet.getString("keyword");
                    String user = resultSet.getString("user");
                    String absolutePath = resultSet.getString("absolutePath");
                    String sourcePath = resultSet.getString("sourcePath");
                    String securityClassification = resultSet.getString("securityClassification");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp");
                    String catalogue = resultSet.getString("catalogue");
                    Archive archive = new Archive();
                    archive.setId(id);
                    archive.setTitle(title);
                    archive.setFileName(fileName);
                    archive.setKeyword(keyword);

                    archive.setUser(new User() {{
                        setName(user);
                    }});

                    archive.setAbsolutePath(absolutePath);
                    archive.setSourcePath(sourcePath);
                    archive.setSecurityClassification(SecurityClassification.getSecurityClassfication(securityClassification));
                    archive.setTimestamp(timestamp);
                    archive.setCatalogue(catalogue);

                    super.insert(archive);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Archive update(Archive archive) throws BaseException {
        return super.update(archive);
    }

    @Override
    public Archive delete(Archive archive) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "delete from archive_info where `id`=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (int) archive.getId());

            int num = statement.executeUpdate();
            if (num > 0) {
                return super.delete(archive);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Archive insert(Archive archive) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "insert into archive_info(`timestamp`,`title`,`keyword`,`catalogue`,`securityClassification`,`fileName`,`absolutePath`,`sourcePath`,`user`,`id`)values (?,?,?,?,?,?,?,?,?,?) ";
            if (connection != null) {
                statement = connection.prepareStatement(sql);
                statement.setTimestamp(1, archive.getTimestamp());
                statement.setString(2, archive.getTitle());
                statement.setString(3, archive.getKeyword());
                statement.setString(4, archive.getCatalogue());
                statement.setString(5, archive.getSecurityClassification().getName());
                statement.setString(6, archive.getFileName());
                statement.setString(7, archive.getAbsolutePath());
                statement.setString(8, archive.getSourcePath());
                statement.setString(9, archive.getUser().getName());
                statement.setInt(10, (int) archive.getId());
                int num = statement.executeUpdate();
                if (num > 0) {
                    return super.insert(archive);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Archive findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Archive> findAllOnes() {
        return super.findAllOnes();
    }
}
