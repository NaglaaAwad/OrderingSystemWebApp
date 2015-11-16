package tutorial.core.services.exceptions;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class ItemExistsException extends RuntimeException {
    public ItemExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemExistsException(String message) {
        super(message);
    }

    public ItemExistsException() {
        super();
    }
}
