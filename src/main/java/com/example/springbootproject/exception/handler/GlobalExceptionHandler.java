package com.example.springbootproject.exception.handler;

import com.example.springbootproject.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<Object> notFoundByName(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<Object> runtimeException(RuntimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
