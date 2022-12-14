package com.sparta.blogpost.util;


import com.sparta.blogpost.dto.ApiExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.TokenMgrError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
//    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ExceptionHandler(IllegalArgumentException.class)
//    @ExceptionHandler({IllegalArgumentException.class, TokenMgrError.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        ApiExceptionDto restApiException = new ApiExceptionDto();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());
        log.warn(ex.getMessage());
        return new ResponseEntity(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }
}