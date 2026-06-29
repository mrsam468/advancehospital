package exception;

public class InvalidPatientNameException extends RuntimeException{
    public InvalidPatientNameException(String message){
        super(message);
    }
}
