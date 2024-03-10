package com.developer.ordermanager.exception;

public class IncorrectStatusException extends RuntimeException{
    public IncorrectStatusException(String message) {
        super(message);
    }
}
