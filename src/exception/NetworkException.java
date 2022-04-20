package exception;

/**
 * @ClassName : NetworkException //类名
 * @Author : 卟言呢
 * @Data : 2021/12/19
 */
public class NetworkException extends BaseException {
    private String message = "网络访问异常";

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
