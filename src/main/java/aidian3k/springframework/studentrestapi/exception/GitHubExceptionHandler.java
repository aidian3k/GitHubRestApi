package aidian3k.springframework.studentrestapi.exception;

import aidian3k.springframework.studentrestapi.controller.GitHubController;
import aidian3k.springframework.studentrestapi.service.GitHubApiService;
import aidian3k.springframework.studentrestapi.webclient.GitHubWebClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice(basePackageClasses= {GitHubWebClient.class, GitHubApiService.class, GitHubController.class})
public class GitHubExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException throwable) {
        ApiExceptionObject apiExceptionObject = new ApiExceptionObject(
                throwable.getMessage(),
                throwable.getClass().getName(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(),
                throwable.getPath()
        );
        return new ResponseEntity<>(apiExceptionObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RequestLimitExceededException.class})
    public ResponseEntity<Object> handleRequestLimit(RequestLimitExceededException throwable) {
        ApiExceptionObject apiExceptionObject = new ApiExceptionObject(
                throwable.getMessage(),
                throwable.getClass().getName(),
                HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                ZonedDateTime.now(),
                throwable.getPath()
        );

        return new ResponseEntity<>(apiExceptionObject, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }
}
