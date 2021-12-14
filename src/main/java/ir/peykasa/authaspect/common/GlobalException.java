package ir.peykasa.authaspect.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class GlobalException extends Exception {
    private final String key;
    private final HttpStatus status;
    private final List<Object> messages = new ArrayList<>();

    public GlobalException(String key, HttpStatus status) {
        this.key = key;
        this.status = status;
    }

    GlobalException(String key, HttpStatus status, Throwable t) {
        super(t);
        this.key = key;
        this.status = status;
    }

}
