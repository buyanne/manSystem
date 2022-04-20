package service;


import dao.ArchiveDao;
import entityClass.Archive;
import exception.BaseException;

import java.io.IOException;
import java.util.List;

public interface ArchiveService {

    /**
     * ע��DAO����
     *
     * @param archiveDao
     */
    void setArchiveDao(ArchiveDao archiveDao);

    /**
     * �ϴ�����
     *
     * @param Archive
     * @return
     * @throws BaseException
     */
    Archive uploadArchive(Archive Archive) throws BaseException;

    /**
     * ���ذ���
     *
     * @param id
     * @param targetPath
     * @return
     * @throws BaseException
     * @throws IOException
     */
    Archive downloadArchive(long id, String targetPath) throws BaseException, IOException;

    /**
     * ɾ������
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Archive  deleteArchive(long id) throws BaseException;

    /**
     * �õ�����
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Archive getArchive(long id) throws BaseException;

    /**
     * �õ����а���
     *
     * @return
     * @throws BaseException
     */
    List<Archive> getAllArchives() throws BaseException;

    /**
     * ������а���
     *
     * @throws BaseException
     */
    void clear() throws BaseException;

    /**
     * �޸İ��ڻ�����Ϣ
     *
     * @param archive
     * @return
     * @throws BaseException
     */
    Archive updateArchive(Archive archive) throws BaseException;
}
