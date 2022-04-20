package action;

import application.Application;
import exception.BaseException;
import service.Impl.ArchiveServiceImpl;
import service.Impl.UserServiceImpl;

import java.util.Scanner;

/**
 * @author ß²ÑÔÄØ
 */
public abstract class BaseAction {
    protected UserServiceImpl userService = Application.userService;
    protected ArchiveServiceImpl archiveService = Application.archiveService;
    protected Scanner sc = new Scanner(System.in);

    public abstract void input() throws BaseException;

    public abstract void execute() throws BaseException;

    public abstract String getText() throws BaseException;
}
