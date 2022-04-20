package dao.Impl;

import common.Constants;
import dao.Impl.ArchiveDaoContainer;
import entityClass.Archive;
import exception.BaseException;

import java.io.*;
import java.util.List;

/**
 * @ClassName : ArchiveDaoFile //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/11/27
 */
public class ArchiveDaoFile extends ArchiveDaoContainer {
    public ArchiveDaoFile() {
        ObjectInputStream objectInputStream = null;
        try {
            File file = new File(Constants.ARCHIVE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Archive archive = null;
            while (null != (archive = (Archive) objectInputStream.readObject())) {
                super.insert(archive);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EOFException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Save() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.ARCHIVE_PATH));
            List<Archive> list = this.findAllOnes();
            for (Archive archive : list) {
                objectOutputStream.writeObject(archive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public Archive insert(Archive archive) {
        Archive archive1 = super.insert(archive);
        Save();
        return archive1;
    }

    @Override
    public Archive update(Archive archive) throws BaseException {
        Archive archive1 = super.update(archive);
        Save();
        return archive1;
    }

    @Override
    public Archive delete(Archive archive) {
        Archive archive1 = super.delete(archive);
        Save();
        return archive1;
    }
}
