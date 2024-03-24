package com.currency.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<Object> emailExistsException(EmailExistsException e){
        ExceptionDetails details = ExceptionDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
        return ResponseEntity.ok(details);
    }
}
