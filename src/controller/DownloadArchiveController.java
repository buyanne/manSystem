package controller;

import entityClass.Archive;
import exception.BaseException;

import java.io.IOException;

/**
 * @ClassName : DownloadArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class DownloadArchiveController extends BaseController {
    @Override
    public void service() {
        try {
            long id = (long) message.getParameter("id");
            Archive archive = archiveService.getArchive(id);
            message.setData(archive);
            onSuccess();

            sendFile(archive.getSourcePath());

        } catch (BaseException| IOException e) {
            e.printStackTrace();
        }
    }
}
