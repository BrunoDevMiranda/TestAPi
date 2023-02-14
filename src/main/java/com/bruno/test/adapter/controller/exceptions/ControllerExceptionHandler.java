package com.bruno.test.adapter.controller.exceptions;

import com.bruno.test.exceptions.DataIntegratyViolationException;
import com.bruno.test.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandError>objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
        StandError error = new StandError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value()
        ,ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandError>dataIntegrityViolationException(DataIntegratyViolationException ex, HttpServletRequest request){
        StandError error = new StandError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()
                ,ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
