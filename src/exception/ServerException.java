package exception;

/**
 * @ClassName : ServerException //����
 * @Author : ߲����
 * @Data : 2021/12/16
 */
public class ServerException extends BaseException{
    private final String message ="������쳣";

    public ServerException(){
        super();
    }

    public ServerException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
