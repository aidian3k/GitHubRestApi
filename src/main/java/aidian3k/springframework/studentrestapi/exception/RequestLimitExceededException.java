package aidian3k.springframework.studentrestapi.exception;

public class RequestLimitExceededException extends RuntimeException{
    private String path;
    public RequestLimitExceededException(String message, String path) {
        super(message);
        this.path = path;
    }

    public RequestLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestLimitExceededException(Throwable cause) {
        super(cause);
    }

    public String getPath() {
        return path;
    }
}
