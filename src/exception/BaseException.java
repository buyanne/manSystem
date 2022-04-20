package exception;

public class BaseException extends Exception {
    protected static final String message = "≥ˆœ÷“Ï≥£";
    private static final long serivalVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
