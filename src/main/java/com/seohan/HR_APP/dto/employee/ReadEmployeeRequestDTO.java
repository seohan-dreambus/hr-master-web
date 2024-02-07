package com.seohan.HR_APP.dto.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadEmployeeRequestDTO {

    @NotBlank
    private String searchKeyword; //검색어

    @NotBlank
    private String searchType; //"name" or "companyNumber" or "departmentCode"

}
