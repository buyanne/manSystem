package service;

import application.ClientApplication;
import common.Constants;
import dao.ArchiveDao;
import entityClass.Archive;
import entityClass.Message;
import entityClass.User;
import exception.BaseException;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName : ArchiveClientService //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/20
 */
public class ArchiveClientService extends BaseServiceClient implements ArchiveService {
    transient private ArchiveDao archiveDao;


    @Override
    public void setArchiveDao(ArchiveDao archiveDao) {
        this.archiveDao = archiveDao;
    }

    @Override
    public Archive uploadArchive(Archive archive) {
        Message message = new Message();
        message.setController(Constants.UPLOAD_ARCHIVE_CONTROLLER);
        archive.setUser(new User() {{
            setName(ClientApplication.currentUser.getName());
        }});
        message.setParameter("archive", archive);
        message = sendArchive(message);
        archive = (Archive) message.getData();
        return archive;
    }

    @Override
    public Archive downloadArchive(long id, String targetPath) throws BaseException, IOException {
        Message message = new Message();
        message.setController(Constants.DOWNLOAD_ARCHIVE_CONTROLLER);
        message.setParameter("id", id);
        message = receiveArchive(message, targetPath);
        Archive archive = (Archive) message.getData();
        return archive;
    }

    @Override
    public Archive deleteArchive(long id) throws BaseException {
        Message message = new Message();
        message.setController(Constants.REMOVE_ARCHIVE_CONTROLLER);
        message.setParameter("id", id);
        message = send(message);
        Archive archive = (Archive) message.getData();
        return archive;
    }

    @Override
    public Archive getArchive(long id) throws BaseException {
        Message message = new Message();
        message.setController(Constants.GET_ARCHIVE_CONTROLLER);
        message.setParameter("id", id);
        message = send(message);
        Archive archive = (Archive) message.getData();
        return archive;
    }

    @Override
    public List<Archive> getAllArchives() throws BaseException {
        Message message = new Message();
        message.setController(Constants.GET_ALL_ARCHIVE_CONTROLLER);
        message = send(message);
        List<Archive> list = (List<Archive>) message.getData();
        return list;
    }

    @Override
    public void clear() throws BaseException {
    }

    @Override
    public Archive updateArchive(Archive archive) throws BaseException {
        return null;
    }
}
