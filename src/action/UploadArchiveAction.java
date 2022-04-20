package action;

import application.Application;
import entityClass.Archive;
import entityClass.SecurityClassification;
import exception.BaseException;

import java.sql.Timestamp;

/**
 * @Author ߲����
 */
@SuppressWarnings("all")
public class UploadArchiveAction extends BaseAction {
    public static final String message = "�ϴ��ļ�";
    Archive archive;

    {
        archive = new Archive();
    }

    @Override
    public void input() {
        //��������id
        try {
            archive.setId(archiveService.getAllArchives().size() + 1);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        System.out.println("�������ļ���");
        archive.setFileName(sc.nextLine());
        System.out.println("���������");
        archive.setTitle(sc.nextLine());
        System.out.println("������ؼ���");
        archive.setKeyword(sc.nextLine());
        System.out.println("��ѡ�񵵰��ܼ�");
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
        System.out.println("������·��");
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
