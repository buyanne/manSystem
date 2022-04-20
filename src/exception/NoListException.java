package exception;

public class NoListException extends BaseException{
    private String message="û���б�";

    public NoListException(){
        super();
    }

    public NoListException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
