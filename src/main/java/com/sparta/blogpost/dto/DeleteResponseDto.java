package com.sparta.blogpost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteResponseDto {
    private boolean success;
    public DeleteResponseDto(boolean reponse){
        this.success = reponse;
    }
}
