package com.tcs.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> custNotFound(CustomerNotFoundException ce)
    {
        System.out.println("control reached here-cus");
        return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentHandling(IllegalArgumentException ie)
    {
        System.out.println("control reached here-ILLEGAL");
        return new ResponseEntity<>(ie.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
