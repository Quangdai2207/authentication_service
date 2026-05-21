package user.service.exception;

public class InternalErrorServerException extends RuntimeException {
    public InternalErrorServerException(String message) {
        super(message);
    }
}
