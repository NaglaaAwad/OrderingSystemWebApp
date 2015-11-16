package tutorial.core.services.exceptions;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException() {
    }
}
