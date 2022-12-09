package com.sparta.bolgpost.dto;

import ch.qos.logback.core.spi.ErrorCodes;
import com.sparta.bolgpost.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ResponseDto<T> {
    boolean success;
    T data;
    Error error;

    public ResponseDto(boolean success, T data){
        this.success = success;
        this.data=data;
    }
    public ResponseDto(boolean success, T data, ErrorCode errorCode){
        this.success = success;
        this.data = data;
        this.error = new Error(errorCode);
    }
    public static class Error{
        private final String code;
        private final String message;

        public Error(ErrorCode errorCode){
            this.code =errorCode.getCode();
            this.message = errorCode.getMessage();
        }
    }
}
