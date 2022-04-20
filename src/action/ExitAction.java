package action;

import application.Application;

public class ExitAction extends BaseAction{
    public static final String message = "ÍË³ö³ÌÐò";
    @Override
    public void input() {

    }

    @Override
    public void execute() {
        System.out.println(message);

        Application.currentUser=null;
        sc.nextLine();
    }

    @Override
    public String getText() {
        return message;
    }
}
