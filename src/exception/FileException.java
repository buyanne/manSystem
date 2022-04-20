package exception;

public class FileException extends BaseException {
    private String message = "ÎÄ¼şÒì³£";

    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
