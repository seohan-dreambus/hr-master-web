package com.seohan.HR_APP.dto.employee;

import com.seohan.HR_APP.domain.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadDepartmentResponseDTO {

    @NotBlank
    private String departmentName; // 부서명

    @NotBlank
    private String departmentChangeId; //부서개편ID

    @NotBlank
    private String departmentCode;//부서코드

    public ReadDepartmentResponseDTO(Department department) {
        this.departmentName = department.getDepartmentName();
        this.departmentChangeId = department.getId().getDepartmentChangeId();
        this.departmentCode = department.getId().getDepartmentCode();
    }
}
