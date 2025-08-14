package com.example.demo.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(HttpServletResponse response) throws IOException {
        response.setStatus(400);
    }

}
