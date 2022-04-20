package action;

import common.Constants;
import entityClass.Archive;
import exception.BaseException;

import java.io.IOException;

/**
 * @author 卟言呢
 */
public class DownloadArchiveAction extends BaseAction {
    public static final String message = "下载文件";


    private int id;

    @Override
    public void input() {
        System.out.println("请输入文件序号序号");
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
