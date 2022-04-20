package controller;

import entityClass.Archive;
import exception.BaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : GetAllArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class GetAllArchiveController extends BaseController {
    @Override
    public void service() {
        List<Archive> list = null;
        try {
            list = archiveService.getAllArchives();
            message.setData(list);
            onSuccess();
        } catch (BaseException e) {
            e.printStackTrace();
        }

    }
}
