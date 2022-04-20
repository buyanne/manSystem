package action;

import application.Application;
import entityClass.User;
import exception.NoListException;
import exception.NoUserException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author : 卟言呢
 * @Description :
 * @Date : 2021/11/17 21:24
 */
public class MeauAction extends BaseAction {

    private List<String> list = null;
    private final String message = "菜单";

    private int choice;


    public void showMenu() {
        System.out.println("[" + Application.currentUser.getName() + "]");
        for (int i = 0; i < list.size(); i++) {
            try {
                //通过反射获取每个action的message
                Class<?> aClass = Class.forName(list.get(i));
                Field field = aClass.getField("message");
                System.out.println(i + 1 + ":" + field.get(aClass));
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void input() {
        System.out.println("请选择序号");
        //list中下标比菜单显示的小1
        choice = sc.nextByte() - 1;
    }

    @Override
    public void execute() {
        try {
            if (Application.currentUser == null) {
                throw new NoUserException();
            }
            //获得User对应role能操作的方法
            list = Application.currentUser.getFunctionClassNames();
            if (list == null) {
                throw new NoListException();
            }
            do {
                showMenu();
                input();
                Class<?> aClass = null;
                try {
                    //利用反射来获得各个类的execute方法并调用
                    if (choice >= list.size()) {
                        throw new IndexOutOfBoundsException("输入错误没有该方法");
                    }
                    //获得选择方法的Class类
                    aClass = Class.forName(list.get(choice));
                    //获得execute方法
                    Method method = aClass.getMethod("execute");
                    //调用execute方法
                    method.invoke(aClass.newInstance());
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                //当输入退出对应的序号时就退出菜单界面
            } while (!"action.ExitAction".equals(list.get(choice)));
        } catch (IndexOutOfBoundsException | NoUserException | NoListException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getText() {
        return message;
    }
}
