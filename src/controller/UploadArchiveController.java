package controller;

import entityClass.Archive;
import exception.BaseException;

/**
 * @ClassName : UploadArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class UploadArchiveController extends BaseController {

    @Override
    public void service() {
        try {
            Archive archive = (Archive) message.getParameter("archive");
            archiveService.uploadArchive(archive);
            message.setData(archive);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
