package action;

import entityClass.Archive;
import exception.BaseException;
import exception.NoArchiveException;

import java.util.Comparator;
import java.util.List;

public class ListArchiveAction extends BaseAction {
    public static final String message = "列出文件";

    @Override
    public void input() {

    }

    @Override
    public void execute() {
        try {
            List<Archive> list = archiveService.getAllArchives();
            if (list == null) {
                throw new NoArchiveException();
            }
            list.sort(new Comparator<Archive>() {
                @Override
                public int compare(Archive archive1, Archive archive2) {
                    return (int)(archive1.getId()-archive2.getId());
                }
            });
            for (Archive archive : list) {
                System.out.println(archive);
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
        sc.nextLine();
    }

    @Override
    public String getText() {
        return message;
    }
}
