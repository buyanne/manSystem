package application;

import dao.Impl.ArchiveDaoJdbc;
import dao.Impl.UserDaoJdbc;
import entityClass.User;
import service.Impl.ArchiveServiceImpl;
import service.Impl.UserServiceImpl;

import java.util.Hashtable;

/**
 * @author ߲����
 */
public class Application {
    transient public static ArchiveServiceImpl archiveService;
    transient public static UserServiceImpl userService;
    transient public static User currentUser;
    transient public static Hashtable<String ,User>hashtable=new Hashtable<>();

    //�ڳ�ʼ����ʱ�������������û���ȥ
    static {
        userService = new UserServiceImpl();
        archiveService = new ArchiveServiceImpl();

        userService.setUserDao(new UserDaoJdbc());

        archiveService.setArchiveDao(new ArchiveDaoJdbc());
    }
}
