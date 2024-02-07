package com.seohan.HR_APP.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank
    private String companyCode;

    @NotBlank
    private String password;
}