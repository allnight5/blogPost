package com.sparta.bolgpost.dto;

import ch.qos.logback.core.spi.ErrorCodes;
import com.sparta.bolgpost.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto<T> {
    private String message;

//    private boolean success;
    T data;
    private int statusCode;
//    Error error;
    public ResponseDto(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }
//    public ResponseDto(String message, int statusCode, T data){
//        this.message = message;
//        this.statusCode = statusCode;
//        this.data = data;
//    }
    public ResponseDto(T data){
        this.data = data;
    }
//    public ResponseDto(boolean success, T data, ErrorCode errorCode){
//        this.success = success;
//        this.data = data;
//        this.error = new Error(errorCode);
//    }
    public static class Error{
        private final String code;
        private final String message;
        public Error(ErrorCode errorCode){
            this.code =errorCode.getCode();
            this.message = errorCode.getMessage();
        }
    }
}
