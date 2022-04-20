package entityClass;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author : ß²ÑÔÄØ
 * @Description : ²Ù×÷Ô±Àà
 * @Date : 2021/11/30 14:51
 */
public class Operator extends User {
    private final List<String> functionClassNames=new ArrayList<String>(){
        {
            add("action.UploadArchiveAction");
            add("action.DownloadArchiveAction");
            add("action.ListArchiveAction");
            add("action.ModifyPasswordAction");
            add("action.ExitAction");
        }
    };

    public Operator() {
        super();
    }

    public Operator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public List<String> getFunctionClassNames() {
        return functionClassNames;
    }
}
