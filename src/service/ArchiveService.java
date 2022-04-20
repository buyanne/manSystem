package service;


import dao.ArchiveDao;
import entityClass.Archive;
import exception.BaseException;

import java.io.IOException;
import java.util.List;

public interface ArchiveService {

    /**
     * 注入DAO对象
     *
     * @param archiveDao
     */
    void setArchiveDao(ArchiveDao archiveDao);

    /**
     * 上传案宗
     *
     * @param Archive
     * @return
     * @throws BaseException
     */
    Archive uploadArchive(Archive Archive) throws BaseException;

    /**
     * 下载案宗
     *
     * @param id
     * @param targetPath
     * @return
     * @throws BaseException
     * @throws IOException
     */
    Archive downloadArchive(long id, String targetPath) throws BaseException, IOException;

    /**
     * 删除案宗
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Archive  deleteArchive(long id) throws BaseException;

    /**
     * 得到案宗
     *
     * @param id
     * @return
     * @throws BaseException
     */
    Archive getArchive(long id) throws BaseException;

    /**
     * 得到所有案宗
     *
     * @return
     * @throws BaseException
     */
    List<Archive> getAllArchives() throws BaseException;

    /**
     * 清空所有案宗
     *
     * @throws BaseException
     */
    void clear() throws BaseException;

    /**
     * 修改案宗基本信息
     *
     * @param archive
     * @return
     * @throws BaseException
     */
    Archive updateArchive(Archive archive) throws BaseException;
}
