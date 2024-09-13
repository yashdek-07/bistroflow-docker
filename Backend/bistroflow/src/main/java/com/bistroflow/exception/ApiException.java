package com.bistroflow.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ApiException(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime timeStamp){
        this.httpStatus = httpStatus;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    //Getters
    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}
