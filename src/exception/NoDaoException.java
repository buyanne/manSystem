package exception;

/**
 * @ClassName : NoDaoException //����
 * @Description : Dao��Ϊ�ջ�û�г�ʼ��Dao
 * @Author : ߲����
 * @Data : 2021/11/20
 */
public class NoDaoException extends BaseException {
    public String message = "Dao��Ϊ�ջ���û�г�ʼ��Dao";

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
