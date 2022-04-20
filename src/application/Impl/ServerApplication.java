package application.Impl;

import application.Application;
import server.Listener;

/**
 * @ClassName : ServerApplication //ÀàÃû
 * @Author : ß²ÑÔÄØ
 * @Data : 2021/12/20
 */
public class ServerApplication extends Application {
    public static void main(String[] args) {
        Listener listener = new Listener();
        listener.Init();
    }
}
