package com.sparta.bolgpost.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String message;
    private int statusCode;

    public MessageResponseDto(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }
    public MessageResponseDto(String message){
        this.message = message;
    }
}
