package auth.service.exceptions;

public class InternalErrorServerException extends RuntimeException {
    public InternalErrorServerException(String message) {
        super(message);
    }
}
