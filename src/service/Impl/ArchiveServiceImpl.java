package service.Impl;

import dao.ArchiveDao;
import entityClass.Archive;
import exception.BaseException;
import exception.NoArchiveException;
import service.ArchiveService;

import java.io.*;
import java.util.List;

/**
 * @ClassName : ArchiveServiceImpl //类名
 * @Author : 卟言呢
 * @Data : 2021/11/23
 */
public class ArchiveServiceImpl implements ArchiveService ,Serializable{
    transient private ArchiveDao archiveDao;

    @Override
    public void setArchiveDao(ArchiveDao archiveDao) {
        this.archiveDao = archiveDao;
    }

    @Override
    public Archive uploadArchive(Archive archive) throws BaseException {
        return archiveDao.insert(archive);
    }

    private void downloadFile(Archive archive, String targetPath) {
        String sourcePath = archive.getAbsolutePath();
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(sourcePath));
            bufferedWriter = new BufferedWriter(new FileWriter(targetPath + archive.getFileName()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Archive createArchive(Archive archive) {
        return archiveDao.insert(archive);
    }

    @Override
    public Archive downloadArchive(long id, String targetPath) throws BaseException, IOException {
        Archive archive = archiveDao.findById(id);
        if (archive == null) {
            throw new NoArchiveException();
        }
        downloadFile(archive, targetPath);
        System.out.println("下载成功");
        return archive;
    }

    @Override
    public synchronized Archive deleteArchive(long id) throws BaseException {
        Archive archive = archiveDao.findById(id);
        return archiveDao.delete(archive);
    }

    @Override
    public Archive getArchive(long id) throws BaseException {
        return archiveDao.findById(id);
    }

    @Override
    public List<Archive> getAllArchives() throws BaseException {
        return archiveDao.findAllOnes();
    }


    @Override
    public void clear() throws BaseException {

    }

    @Override
    public synchronized Archive updateArchive(Archive archive) throws BaseException {
        return null;
    }
}
