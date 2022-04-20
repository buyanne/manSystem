package frame;

import application.Application;
import application.ClientApplication;
import entityClass.User;
import service.ArchiveService;
import service.UserService;

import javax.swing.*;

/**
 * @ClassName : BaseFrame //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/11/30
 */
public class BaseFrame extends JFrame {

    protected User user = ClientApplication.currentUser;
    protected UserService userService = ClientApplication.userService;
    protected ArchiveService archiveService = ClientApplication.archiveService;
}
