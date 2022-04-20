package exception;

/**
 * @ClassName : NoDaoException //类名
 * @Description : Dao类为空或没有初始化Dao
 * @Author : 卟言呢
 * @Data : 2021/11/20
 */
public class NoDaoException extends BaseException {
    public String message = "Dao类为空或者没有初始化Dao";

    public NoDaoException() {
        super();
    }

    public NoDaoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
