package entityClass;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : buyan
 * @Description : 档案实体类
 */
@SuppressWarnings("all")
public class Archive implements Serializable {
    private static final long serialVersionUID = 8781005320460274471L;
    private long id;                //唯一标识符
    private Timestamp timestamp;   //更新时间戳
    private String title;           //标题
    private String keyword;   //关键词
    private String catalogue; //目录
    private SecurityClassification securityClassification;   //档案密级
    private String fileName;  //文件名
    private String absolutePath;  //附件在后台的绝对路径
    private String sourcePath; //前台的绝对路径，不持久化
    private User user;    //上传用户

    //TODO
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public SecurityClassification getSecurityClassification() {
        return this.securityClassification;
    }

    public void setSecurityClassification(Enum<SecurityClassification> securityClassification) {
        this.securityClassification =Enum.valueOf(SecurityClassification.class,securityClassification.name());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", 文件名='" + fileName + '\'' +
                ", 上传者=" + user.getName() +
                ", 标题='" + title + '\'' +
                ", 关键词='" + keyword + '\'' +
                ", 创建时间=" + timestamp +
                "， 文件路径=" + sourcePath +
                '}';
    }
}
