package gestgym.com.gestgym.exceptions;



public class RessourceAlreadyExistException extends Exception {

    public RessourceAlreadyExistException(String message) {
        super(message);
    }

    public RessourceAlreadyExistException(String message , Throwable cause)
    {
        super(message, cause);
    }
}
