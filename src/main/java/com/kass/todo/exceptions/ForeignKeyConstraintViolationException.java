package com.kass.todo.exceptions;

public class ForeignKeyConstraintViolationException extends RuntimeException {
    public ForeignKeyConstraintViolationException(String message) {
        super(message);
    }
}
