package action;

import application.Application;
import entityClass.Archive;
import entityClass.SecurityClassification;
import exception.BaseException;

import java.sql.Timestamp;

/**
 * @Author 卟言呢
 */
@SuppressWarnings("all")
public class UploadArchiveAction extends BaseAction {
    public static final String message = "上传文件";
    Archive archive;

    {
        archive = new Archive();
    }

    @Override
    public void input() {
        //设置自增id
        try {
            archive.setId(archiveService.getAllArchives().size() + 1);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入文件名");
        archive.setFileName(sc.nextLine());
        System.out.println("请输入标题");
        archive.setTitle(sc.nextLine());
        System.out.println("请输入关键词");
        archive.setKeyword(sc.nextLine());
        System.out.println("请选择档案密级");
        System.out.println("" +
                "1-A\n" +
                "2-B\n" +
                "3-C\n" +
                "4-D\n");
        int choice = sc.nextByte();
        SecurityClassification securityClassification = null;
        switch (choice) {
            case 1:
                securityClassification = SecurityClassification.A;
                break;
            case 2:
                securityClassification = SecurityClassification.B;
                break;
            case 3:
                securityClassification = SecurityClassification.C;
                break;
            case 4:
                securityClassification = SecurityClassification.D;
                break;
            default:
                break;
        }
        archive.setSecurityClassification(securityClassification);
        archive.setCatalogue("0000");
        archive.setTimestamp(new Timestamp(System.currentTimeMillis()));
        System.out.println("请输入路径");
        String path = sc.nextLine();
        archive.setAbsolutePath(path);
        archive.setSourcePath(path);
        archive.setUser(Application.currentUser);
    }

    @Override
    public void execute() {
        input();
        archiveService.createArchive(archive);
        sc.nextLine();
    }

    @Override
    public String getText() {
        return message;
    }
}
