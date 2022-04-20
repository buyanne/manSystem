package application;

import entityClass.User;
import service.ArchiveClientService;
import service.ArchiveService;
import service.ClientServiceImpl.UserClientService;
import service.UserService;

/**
 * @ClassName : ApplicatjionClient //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/20
 */
public class ClientApplication {
    transient public static User currentUser;
    transient public static UserService userService;
    transient public static ArchiveService archiveService;

    static {
        userService = new UserClientService();
        archiveService = new ArchiveClientService();
    }
}
