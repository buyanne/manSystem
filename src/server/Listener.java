package server;

import common.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : Listener //类名
 * @Author : 卟言呢
 * @Data : 2021/12/19
 */
public class Listener {
    private final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            5,
            5,
            Runtime.getRuntime().availableProcessors(),
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy());

    public void Init() {
        try (ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT)) {
            System.out.println("Listening on " + serverSocket);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println(client.getInetAddress().getHostName() + " is connecting!");

                poolExecutor.execute(new MyThread(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();

        }
    }

    private static class MyThread implements Runnable {
        final transient private Socket socket;
        transient private ObjectInputStream in;
        transient private ObjectOutputStream out;


        public MyThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is running");
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                Dispatcher.forward(in, out);
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

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("服务器异常");
                    }
                }

            }
        }
    }


}
