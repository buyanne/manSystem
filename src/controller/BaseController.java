package controller;

import application.Application;
import common.Constants;
import entityClass.Message;
import exception.BaseException;
import service.ArchiveService;
import service.UserService;

import java.io.*;

/**
 * @ClassName : BaseController //类名
 * @Author : 卟言呢
 * @Data : 2021/12/16
 */
public class BaseController{
    protected UserService userService = Application.userService;
    protected ArchiveService archiveService = Application.archiveService;
    protected Message message;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void service() {

    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void onException(BaseException exception) {
        message.setCode(Constants.SERVER_EXCEPTION);
        message.setMessage(exception.getMessage());

        try {
            out.writeObject(message);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onSuccess() {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sendFile(String path) throws IOException {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        out.writeLong(file.length());
        out.flush();

        byte[] buffer = new byte[1024];
        while (true) {
            int read = 0;
            read = fileInputStream.read(buffer);
            if (read == -1) {
                break;
            }
            out.write(buffer, 0, read);
            out.flush();
        }
    }

    protected String receiveTempFile(String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            long fileLength = in.readLong();
            String tempPath = Constants.DOWNLOAD_PATH;
            File file = new File(tempPath);
            fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int transLen = 0;
            System.out.println("开始接收文件" + fileName);
            while (transLen < fileLength) {
                int read = 0;
                read = in.read(buffer);
                System.out.println(read);
                if (read != -1) {
                    transLen += read;
                    System.out.println("接收文件进度" + 100L * transLen / fileLength + "%");
                    fileOutputStream.write(buffer, 0, read);
                    fileOutputStream.flush();
                } else {
                    break;
                }
            }
            fileOutputStream.close();
            System.out.println("接收文件成功");
            return tempPath;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    protected boolean deleteTempFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败");
            return false;
        }
    }
}
