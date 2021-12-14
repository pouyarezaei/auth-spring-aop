package ir.peykasa.authaspect.auth.exception;

import ir.peykasa.authaspect.common.Constants;
import ir.peykasa.authaspect.common.GlobalException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AccessDeniedException extends GlobalException {
    public AccessDeniedException(String message) {
        super(Constants.ERROR_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        this.getMessages().add(message);
    }

    public AccessDeniedException(List<?> messages) {
        super(Constants.ERROR_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        this.getMessages().addAll(messages);
    }
}
