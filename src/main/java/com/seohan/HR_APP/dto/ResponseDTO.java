package com.seohan.HR_APP.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseDTO(T data, String message){
        success = true;
        this.data = data;
        this.message = message;
    }
}