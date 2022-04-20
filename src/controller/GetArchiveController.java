package controller;

import entityClass.Archive;
import exception.BaseException;

/**
 * @ClassName : GetArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class GetArchiveController extends BaseController {
    @Override
    public void service() {
        try {
            long id = (long) message.getParameter("id");
            Archive archive = archiveService.getArchive(id);
            message.setData(archive);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
