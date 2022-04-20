package controller;

import entityClass.Archive;
import exception.BaseException;

/**
 * @ClassName : RemoveArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class RemoveArchiveController extends BaseController {

    @Override
    public void service() {
        try {
            Archive archive = (Archive) message.getParameter("archive");
            archiveService.deleteArchive(archive.getId());
            message.setData(archive);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
