package entityClass;

import java.util.ArrayList;
import java.util.List;


public class Browser extends User{
    private final List<String> list = new ArrayList<String>() {
        {
            add("action.DownloadArchiveAction");
            add("action.ListArchiveAction");
            add("action.ModifyPasswordAction");
            add("action.ExitAction");
        }
    };

    public Browser() {
        super();
    }

    public Browser(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public List<String> getFunctionClassNames() {
        return list;
    }


}
