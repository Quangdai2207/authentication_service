package auth.service.exceptions;

public class AuthenticationExceptionHandler extends RuntimeException {
    public AuthenticationExceptionHandler(String message) {
        super(message);
    }
}
