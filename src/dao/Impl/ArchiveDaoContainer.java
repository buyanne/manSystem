package dao.Impl;

import com.mysql.cj.xdevapi.Client;
import dao.ArchiveDao;
import entityClass.Archive;
import exception.BaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @ClassName : ArchiveDaoContainer //类名
 * @Description : 案宗的Dao
 * @Author : 卟言呢
 * @Data : 2021/11/23
 */
public class  ArchiveDaoContainer implements ArchiveDao ,Serializable  {
    private static final Hashtable<String, Archive> ARCHIVES = new Hashtable<>();

    @Override
    public Archive insert(Archive archive) {
        return ARCHIVES.put(archive.getFileName(), archive);
    }

    @Override
    public Archive update(Archive archive) throws BaseException {
        return ARCHIVES.put(archive.getFileName(), archive);
    }

    @Override
    public Archive delete(Archive archive) {
        return ARCHIVES.remove(archive.getFileName());
    }

    @Override
    public Archive findByName(Archive archive) throws BaseException {
        return ARCHIVES.get(archive.getFileName());
    }

    @Override
    public Archive findById(Long id) {
        List<Archive> list = findAllOnes();
        for (Archive archive : list) {
            if (archive.getId() == id) {
                return archive;
            }
        }
        return null;
    }

    @Override
    public List<Archive> findAllOnes() {
        return new ArrayList<>(ARCHIVES.values());
    }

    public static Archive search(String fileName) {
        if (ARCHIVES.containsKey(fileName)) {
            return ARCHIVES.get(fileName);
        }
        return null;
    }
}
