package com.kass.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForeignKeyConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleForeignKeyConstraintViolationException(ForeignKeyConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistsNameCategoryException.class)
    public ResponseEntity<ErrorResponse> handleExistsNameCategoryExceptionException(ExistsNameCategoryException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

