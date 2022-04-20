package exception;

/**
 * @ClassName : NetworkException //����
 * @Author : ߲����
 * @Data : 2021/12/19
 */
public class NetworkException extends BaseException {
    private String message = "��������쳣";

    public NetworkException() {
        super();
    }

    public NetworkException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
