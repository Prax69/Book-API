package com.springbook.bookapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class BookRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(Exception exc) {
        // Create a BookErrorResponse
        BookErrorResponse error = new BookErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("This is a bad request, please check your input.");
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exc) {
        // Create a BookErrorResponse
        BookErrorResponse error = new BookErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    //    User is not authenticated to access the resource
    @ExceptionHandler
    public ResponseEntity<AuthenticationErrorResponse> handleException(AccessDeniedException exc) {
        AuthenticationErrorResponse error = new AuthenticationErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage("You do not have permission to access this resource.");
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}