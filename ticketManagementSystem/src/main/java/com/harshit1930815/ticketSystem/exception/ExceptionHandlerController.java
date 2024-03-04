package com.harshit1930815.ticketSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.harshit1930815.ticketSystem.payload.ApiResponse;

//11. Write Exception Handler Class using ControllerAdvice - 10 points

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(InvalidOperationException ex) {
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setSuccess(true);
        response.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
}
