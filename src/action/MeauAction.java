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
 * @Author : ߲����
 * @Description :
 * @Date : 2021/11/17 21:24
 */
public class MeauAction extends BaseAction {

    private List<String> list = null;
    private final String message = "�˵�";

    private int choice;


    public void showMenu() {
        System.out.println("[" + Application.currentUser.getName() + "]");
        for (int i = 0; i < list.size(); i++) {
            try {
                //ͨ�������ȡÿ��action��message
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
        System.out.println("��ѡ�����");
        //list���±�Ȳ˵���ʾ��С1
        choice = sc.nextByte() - 1;
    }

    @Override
    public void execute() {
        try {
            if (Application.currentUser == null) {
                throw new NoUserException();
            }
            //���User��Ӧrole�ܲ����ķ���
            list = Application.currentUser.getFunctionClassNames();
            if (list == null) {
                throw new NoListException();
            }
            do {
                showMenu();
                input();
                Class<?> aClass = null;
                try {
                    //���÷�������ø������execute����������
                    if (choice >= list.size()) {
                        throw new IndexOutOfBoundsException("�������û�и÷���");
                    }
                    //���ѡ�񷽷���Class��
                    aClass = Class.forName(list.get(choice));
                    //���execute����
                    Method method = aClass.getMethod("execute");
                    //����execute����
                    method.invoke(aClass.newInstance());
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
                //�������˳���Ӧ�����ʱ���˳��˵�����
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
