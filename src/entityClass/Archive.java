package entityClass;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : buyan
 * @Description : ����ʵ����
 */
@SuppressWarnings("all")
public class Archive implements Serializable {
    private static final long serialVersionUID = 8781005320460274471L;
    private long id;                //Ψһ��ʶ��
    private Timestamp timestamp;   //����ʱ���
    private String title;           //����
    private String keyword;   //�ؼ���
    private String catalogue; //Ŀ¼
    private SecurityClassification securityClassification;   //�����ܼ�
    private String fileName;  //�ļ���
    private String absolutePath;  //�����ں�̨�ľ���·��
    private String sourcePath; //ǰ̨�ľ���·�������־û�
    private User user;    //�ϴ��û�

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
                ", �ļ���='" + fileName + '\'' +
                ", �ϴ���=" + user.getName() +
                ", ����='" + title + '\'' +
                ", �ؼ���='" + keyword + '\'' +
                ", ����ʱ��=" + timestamp +
                "�� �ļ�·��=" + sourcePath +
                '}';
    }
}
