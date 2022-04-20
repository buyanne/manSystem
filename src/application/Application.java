package application;

import dao.Impl.ArchiveDaoJdbc;
import dao.Impl.UserDaoJdbc;
import entityClass.User;
import service.Impl.ArchiveServiceImpl;
import service.Impl.UserServiceImpl;

import java.util.Hashtable;

/**
 * @author 卟言呢
 */
public class Application {
    transient public static ArchiveServiceImpl archiveService;
    transient public static UserServiceImpl userService;
    transient public static User currentUser;
    transient public static Hashtable<String ,User>hashtable=new Hashtable<>();

    //在初始化的时候就先添加三个用户进去
    static {
        userService = new UserServiceImpl();
        archiveService = new ArchiveServiceImpl();

        userService.setUserDao(new UserDaoJdbc());

        archiveService.setArchiveDao(new ArchiveDaoJdbc());
    }
}
