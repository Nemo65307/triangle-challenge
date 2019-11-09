package com.nemo.trianglechallenge.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(HttpServletRequest request, Exception e) {
        logger.error("Something went wrong... Requested URL: " + request.getRequestURL() +
                " The following exception occurred: " + e);
        return new ResponseEntity<>("Something went wrong on the server side",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
