package tutorial.core.services.exceptions;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException() {
        super();
    }
}

