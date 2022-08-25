package aidian3k.springframework.studentrestapi.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiExceptionObject(String message, String throwable, HttpStatus httpStatus, ZonedDateTime dateTime, String path) {
}
