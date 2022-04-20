package exception;

/**
 * @ClassName : NoArchiveException //类名
 * @Author : 卟言呢
 * @Data : 2021/11/23
 */
public class NoArchiveException extends BaseException{
    private final String message="没有该文件";

    public NoArchiveException(){
        super();
    }
    public NoArchiveException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
