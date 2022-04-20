package action;

import common.Constants;
import entityClass.Archive;
import exception.BaseException;

import java.io.IOException;

/**
 * @author ߲����
 */
public class DownloadArchiveAction extends BaseAction {
    public static final String message = "�����ļ�";


    private int id;

    @Override
    public void input() {
        System.out.println("�������ļ�������");
        id = sc.nextByte();
    }

    @Override
    public void execute() {
        try {
            input();
            archiveService.downloadArchive(id, Constants.DOWNLOAD_PATH);
        } catch (BaseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getText() {
        return message;
    }
}
