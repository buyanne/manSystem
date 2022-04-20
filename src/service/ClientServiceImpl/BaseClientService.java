package service.ClientServiceImpl;

import common.Constants;
import entityClass.Message;
import exception.ServerException;
import entityClass.Archive;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @ClassName : BaseClientService //类名
 * @Author : 卟言呢
 * @Data : 2021/12/19
 */
public class BaseClientService {
    transient protected Socket client;
    transient protected ObjectOutputStream out;
    transient protected ObjectInputStream in;

    protected void checkStatusCode(Message message) throws ServerException {
        if (message.getCode() == Constants.SERVER_EXCEPTION) {
            throw new ServerException();
        }
    }

    protected Message send(Message message) {
        try {
            client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(message);
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
            message = (Message) in.readObject();
            checkStatusCode(message);
        } catch (IOException | ClassNotFoundException | ServerException exception) {
            System.out.println(exception.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
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
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(message);
            out.flush();
            Archive archive = (Archive) message.getParameter("archive");
            //TODO 要写发送文件
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

            if (in != null) {
                try {
                    in.close();
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

    //TODO receiveFile(),sendFile(),receiveArchive()

    protected Message receiveArchive(Message message, String targetPath) {
        try {
            client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(message);
            out.flush();

            in = new ObjectInputStream(client.getInputStream());
            message = (Message) in.readObject();
            checkStatusCode(message);

            Archive archive = (Archive) message.getParameter("archive");
            receiveFile(archive.getFileName(), targetPath);
        } catch (IOException | ClassNotFoundException | ServerException exception) {
            exception.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
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


    protected void sendFile(String sourcePath) {
        File file = new File(sourcePath);
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(file);

            out.writeLong(file.length());
            out.flush();

            byte[] buffer = new byte[1024];
            while (true) {
                int read = 0;
                read = stream.read(buffer);
                if (read == -1) {
                    break;
                }
                out.write(buffer, 0, read);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void receiveFile(String fileName, String targetPath) {
        FileOutputStream fileOutputStream = null;
        try {
            long fileLength = in.readLong();
            targetPath = targetPath != null ? targetPath : Constants.DOWNLOAD_PATH + fileName;
            fileOutputStream = new FileOutputStream(targetPath);

            byte[] buffer = new byte[1024];
            int transLen = 0;
            System.out.println("开始接收文件");
            while (transLen < fileLength) {
                int read = 0;
                read = in.read(buffer);
                System.out.println(read);
                if (read == -1) {
                    break;
                }
                transLen += read;
                System.out.println("接收文件进度" + 100L * transLen / read + "%");
                fileOutputStream.write(buffer, 0, read);
            }

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

    }
}
