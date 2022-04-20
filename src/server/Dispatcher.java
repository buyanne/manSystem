package server;

import common.Constants;
import controller.BaseController;
import entityClass.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * @ClassName : Dispatcher //¿‡√˚
 * @Author : ﬂ≤—‘ƒÿ
 * @Data : 2021/12/19
 */
public class Dispatcher {

    private static HashMap<String, BaseController> controllers = new HashMap<>();

    static {
        for (String controllerClassname : Constants.controllerNames) {
            BaseController controller;
            try {
                controller = (BaseController) Class.forName(controllerClassname).newInstance();
                controllers.put(controller.getClass().getName(), controller);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void forward(ObjectInputStream in, ObjectOutputStream out) {
        try {
            Message message = (Message) in.readObject();
            BaseController controller = controllers.get(message.getController());
            controller.setOut(out);
            controller.setIn(in);
            controller.setMessage(message);
            System.out.println(controller.getClass().getSimpleName() + " is serving");
            controller.service();
            System.out.println("----------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
