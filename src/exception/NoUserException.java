package exception;

/**
 * @author ߲����
 */
public class NoUserException extends BaseException {
    public String message = "û�и��û�";


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
