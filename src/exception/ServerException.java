package exception;

/**
 * @ClassName : ServerException //类名
 * @Author : 卟言呢
 * @Data : 2021/12/16
 */
public class ServerException extends BaseException{
    private final String message ="服务端异常";

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
