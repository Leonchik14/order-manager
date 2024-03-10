package com.developer.ordermanager.contoller;

import com.developer.ordermanager.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalReviewException.class)
    public ResponseEntity<String> handleIllegalReviewException(IllegalReviewException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectIdException.class)
    public ResponseEntity<String> handleIncorrectIdException(IncorrectIdException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectQuantityException.class)
    public ResponseEntity<String> handleIncorrectQuantityException(IncorrectQuantityException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectStatusException.class)
    public ResponseEntity<String> handleIncorrectStatusException(IncorrectStatusException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectUserException.class)
    public ResponseEntity<String> handleIncorrectUserException(IncorrectUserException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
