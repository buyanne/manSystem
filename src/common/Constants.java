package common;

import java.util.ArrayList;
import java.util.List;

public interface Constants {
    String PROJECT_PATH = "D:/Java/AllProject/Experiment";
    String USER_PATH = PROJECT_PATH + "/Info/user.bin";
    String ARCHIVE_PATH = PROJECT_PATH + "/Info/archive.bin";

    String DOWNLOAD_PATH = PROJECT_PATH + "/DownloadFile/";

    String SERVER_ADDRESS = "192.168.56.1";

    String DRIVER = "com.mysql.cj.jdbc.Driver";

    String URL = "jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf8";

    String USERNAME = "Buyan";

    String PASSWORD = "Wang1277321189";

    int SERVER_PORT = 9000;

    int SERVER_EXCEPTION = -1;

    String CHANGE_PASSWORD_CONTROLLER = "controller.ChangePasswordController";
    String ADD_USER_CONTROLLER = "controller.AddUserController";
    String ADD_ARCHIVE_CONTROLLER = "controller.AddArchiveController";
    String GET_USER_CONTROLLER = "controller.GetUserController";
    String GET_ALL_USERS_CONTROLLER = "controller.GetAllUserController";
    String GET_ALL_ARCHIVE_CONTROLLER = "controller.GetAllArchiveController";
    String GET_ARCHIVE_CONTROLLER="controller.GetArchiveController";
    String DOWNLOAD_ARCHIVE_CONTROLLER = "controller.DownloadArchiveController";
    String REMOVE_USER_CONTROLLER = "controller.RemoveUserController";
    String REMOVE_ARCHIVE_CONTROLLER = "controller.RemoveArchiveController";
    String UPDATE_USER_CONTROLLER = "controller.UpdateUserController";
    String UPLOAD_ARCHIVE_CONTROLLER = "controller.UploadArchiveController";
    String LOGIN_CONTROLLER ="controller.LoginController";


    List<String> controllerNames = new ArrayList<String>() {{
        add("controller.AddArchiveController");
        add("controller.AddUserController");
        add("controller.BaseController");
        add("controller.ChangePasswordController");
        add("controller.DownloadArchiveController");
        add("controller.GetAllArchiveController");
        add("controller.GetAllUserController");
        add("controller.GetArchiveController");
        add("controller.GetUserController");
        add("controller.LoginController");
        add("controller.RemoveUserController");
        add("controller.UpdateUserController");
        add("controller.UploadArchiveController");

    }};
}
