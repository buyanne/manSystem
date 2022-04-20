package exception;

/**
 * @ClassName : NoArchiveException //����
 * @Author : ߲����
 * @Data : 2021/11/23
 */
public class NoArchiveException extends BaseException{
    private final String message="û�и��ļ�";

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
