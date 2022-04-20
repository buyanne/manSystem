package service;

import common.Constants;
import entityClass.Archive;
import entityClass.Message;
import exception.ServerException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName : BaseServiceClient //类名
 * @Author : 卟言呢
 * @Data : 2021/12/16
 */
public abstract class BaseServiceClient implements Serializable{
    transient protected Socket client;
    transient protected ObjectOutputStream outputStream;
    transient protected ObjectInputStream inputStream;

    protected void checkStatusCode( Message message) throws ServerException {
        if (message.getCode() == 5000) {
            throw new ServerException();
        }
    }

    protected Message send(Message message) {
        try {
            client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
            outputStream = new ObjectOutputStream((client.getOutputStream()));
            outputStream.writeObject(message);
            outputStream.flush();
            inputStream = new ObjectInputStream(client.getInputStream());
            message = (Message) inputStream.readObject();
            checkStatusCode(message);
        } catch (IOException | ClassNotFoundException | ServerException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return message;
    }


    protected Message sendArchive(Message message) {
        try {
            client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(message);
            outputStream.flush();

            Archive archive = (Archive) message.getParameter("archive");
            sendFile(archive.getSourcePath());
            inputStream = new ObjectInputStream(client.getInputStream());
            message = (Message) inputStream.readObject();
            checkStatusCode(message);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return message;
    }

    protected Message receiveArchive(Message message, String targetPath) {
        try {
            client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(message);
            outputStream.flush();

            inputStream = new ObjectInputStream(client.getInputStream());
            message = (Message) inputStream.readObject();
            checkStatusCode(message);

            Archive archive = (Archive) message.getData();
            receiveFile(archive.getFileName(), targetPath);
        } catch (IOException | ServerException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return message;

    }


    protected void sendFile(String sourcePath) throws IOException {
        File file = new File(sourcePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        outputStream.writeLong(file.length());
        outputStream.flush();

        byte[] buffer = new byte[1024];
        while (true) {
            int read = 0;
            read = fileInputStream.read(buffer);
            if (read == -1) {
                break;
            }
            outputStream.write(buffer, 0, read);
            outputStream.flush();
        }
        fileInputStream.close();
    }

    protected void receiveFile(String fileName, String targetPath) throws IOException {
        long fileLength = inputStream.readLong();
        targetPath = targetPath != null ? targetPath+"/"+fileName : Constants.DOWNLOAD_PATH;
        FileOutputStream fileOutputStream = new FileOutputStream(targetPath);

        byte[] buffer = new byte[1024];
        int transLen = 0;
        System.out.println("开始接收文件<" + fileName + ">,文件大小为<" + fileLength + ">");
        while (transLen < fileLength) {
            int read = 0;
            read = inputStream.read(buffer);
            System.out.println(read);
            if (read == -1) {
                break;
            }
            transLen += read;
            System.out.println("接收文件进度" + 100L * transLen / fileLength + "%");
            fileOutputStream.write(buffer, 0, read);
            fileOutputStream.flush();
        }
        fileOutputStream.close();
        System.out.println("接收文件成功 ");
    }
}
