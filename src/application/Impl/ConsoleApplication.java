package application.Impl;

import action.LoginAction;
import action.MeauAction;
import application.ClientApplication;


public class ConsoleApplication extends ClientApplication {
    public static void main(String[] args) {
        login();
        System.out.println("hello,world");
    }


    public static void login() {
        while (true) {
            LoginAction loginAction = new LoginAction();
            loginAction.execute();
            if (ClientApplication.currentUser != null) {
                MeauAction meauAction = new MeauAction();
                meauAction.execute();
            }
        }
    }
}
