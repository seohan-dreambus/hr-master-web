package com.seohan.HR_APP.dto.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteEmployeeRequestDTO {

    @NotBlank
    private String companyId;//사번

    private Date resignationDate; //퇴사일자

    private String resignationReason; //퇴사 사유

}
