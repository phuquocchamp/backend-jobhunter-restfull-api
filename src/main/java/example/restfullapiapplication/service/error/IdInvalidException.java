package example.restfullapiapplication.service.error;

public class IdInvalidException extends Exception{
    public IdInvalidException(String message){
        super(message);
    }
}
