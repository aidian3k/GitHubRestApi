package aidian3k.springframework.studentrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    private String path;

    public UserNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public String getPath() {
        return path;
    }
}
