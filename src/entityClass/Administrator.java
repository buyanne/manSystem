package entityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ߲����
 * @description ����Ա��
 */
public class Administrator extends User {
    private final List<String> list = new ArrayList<String>() {
        {
            add("action.AddUserAction");
            add("action.ModifyUserInfoAction");
            add("action.RemoveUserAction");
            add("action.ListUserAction");
            add("action.ListArchiveAction");
            add("action.DownloadArchiveAction");
            add("action.ModifyPasswordAction");
            add("action.ExitAction");
        }
    };

    public Administrator() {
        super();
    }

    public Administrator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public List<String> getFunctionClassNames() {
        return list;
    }
}
