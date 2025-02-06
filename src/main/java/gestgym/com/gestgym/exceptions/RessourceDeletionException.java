package gestgym.com.gestgym.exceptions;

public class RessourceDeletionException extends Exception {
    public RessourceDeletionException(String message) {
        super(message);
    }

    public RessourceDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
