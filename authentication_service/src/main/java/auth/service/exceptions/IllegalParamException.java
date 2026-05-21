package auth.service.exceptions;

public class IllegalParamException extends RuntimeException {
    public IllegalParamException(String message) {
        super(message);
    }
}
