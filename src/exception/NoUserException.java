package exception;

/**
 * @author 卟言呢
 */
public class NoUserException extends BaseException {
    public String message = "没有该用户";


    public NoUserException(String string) {
        super(string);
    }

    public NoUserException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
