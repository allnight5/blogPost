package com.sparta.bolgpost.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private boolean success;
    public DeleteResponseDto(boolean reponse){
        this.success = reponse;
    }
}
