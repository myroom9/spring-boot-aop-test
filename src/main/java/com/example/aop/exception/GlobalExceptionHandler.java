package com.example.aop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity handleBaseException(HttpServletRequest request, BaseException e) {
        log.info("handleBaseException!");
        log.error(String.valueOf(request.getMethod()));
        log.error(String.valueOf(request.getRequestURL()));

        return new ResponseEntity<>("GlobalExceptionHandler.handleException" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception e) {
        log.info("handleException!");
        log.error(String.valueOf(request.getRequestURL()));
        return new ResponseEntity<>("GlobalExceptionHandler.handleException" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
