package com.example.weatherApp.controllers.impl;

import com.example.weatherApp.dto.ErrorDTO;
import com.example.weatherApp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherAdviseController {
    @ExceptionHandler(NotFoundException.class)

    public ResponseEntity<ErrorDTO> NotFoundError(NotFoundException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> BadRequestError(BadRequestException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @ExceptionHandler(JsonErrorMapping.class)
    public ResponseEntity<ErrorDTO> errorParsingJson(JsonErrorMapping exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return null;
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorDTO> internalError(InternalErrorException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @ExceptionHandler(RequestTimeOutException.class)
    public ResponseEntity<ErrorDTO> requestTimeOut(RequestTimeOutException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.REQUEST_TIMEOUT);
        }
        return null;
    }

    @ExceptionHandler(ResponseErrorException.class)
    public ResponseEntity<ErrorDTO> errorWithTheResponse(ResponseErrorException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.REQUEST_TIMEOUT);
        }
        return null;
    }

    @ExceptionHandler(ExecutingRequestException.class)
    public ResponseEntity<ErrorDTO> errorWhenExecuteRequest(ExecutingRequestException exception) {
        if (exception != null) {
            ErrorDTO ErrorDTO = new ErrorDTO(exception.getMessage());
            return new ResponseEntity<>(ErrorDTO, HttpStatus.REQUEST_TIMEOUT);
        }
        return null;
    }
}
