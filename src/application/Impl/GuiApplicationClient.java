package application.Impl;

import application.ClientApplication;
import frame.LoginFrame;

/**
 * @ClassName : GUIApplication //����
 * @Author : ߲����
 * @Data : 2021/12/4
 */
public class GuiApplicationClient extends ClientApplication {
    public static void main(String[] args) {
        new LoginFrame();
    }

}
