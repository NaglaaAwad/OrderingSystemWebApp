package tutorial.core.services.exceptions;

/**
 * Created by Naglaa on 10/19/2015.
 */

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException() {
    }
}