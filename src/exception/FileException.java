package exception;

public class FileException extends BaseException {
    private String message = "�ļ��쳣";

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
