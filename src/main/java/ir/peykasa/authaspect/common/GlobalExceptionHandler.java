package ir.peykasa.authaspect.common;

import ir.peykasa.authaspect.auth.exception.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestErrorResponse> handleException(AccessDeniedException ex) {
        return new ResponseEntity<>(new RestErrorResponse(ex.getKey(), ex.getMessages()), HttpStatus.UNAUTHORIZED);
    }
}
