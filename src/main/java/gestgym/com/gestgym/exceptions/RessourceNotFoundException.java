package gestgym.com.gestgym.exceptions;

public class RessourceNotFoundException extends Exception {
    public RessourceNotFoundException(String message) {
        super(message);
    }

    public RessourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
