package controller;

import entityClass.Archive;
import exception.BaseException;

/**
 * @ClassName : AddArchiveController //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class AddArchiveController extends BaseController{
    @Override
    public void service(){
        Archive archive=(Archive)message.getParameter("archive");
        String tempPath=receiveTempFile(archive.getFileName());
        archive.setSourcePath(tempPath);
        archive.setAbsolutePath(tempPath);
        deleteTempFile(tempPath);
        message.setData(archive);
        onSuccess();
    }
}
