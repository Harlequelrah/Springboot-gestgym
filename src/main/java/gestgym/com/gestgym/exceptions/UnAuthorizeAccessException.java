package gestgym.com.gestgym.exceptions;

public class UnAuthorizeAccessException extends Exception {

    public UnAuthorizeAccessException(String message)
    {
        super(message);
    }

    public UnAuthorizeAccessException(String message , Throwable cause)
    {
        super(message, cause);
    }

}
