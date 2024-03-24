package com.currency.Backend.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
    private int statusCode;
    private LocalDateTime timeStamp;
    private String message;
    private String status;
}
