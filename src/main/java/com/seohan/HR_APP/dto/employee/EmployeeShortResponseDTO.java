package com.seohan.HR_APP.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.enumType.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeShortResponseDTO {

    @NotBlank
    private String companyId; //사번

    @NotBlank
    private String name; //이름

    @NotBlank
    private String department;// 부서

    @NotNull
    private PositionType position;// 직책

    @NotNull
    private PositionLankType positionLank;// 직급

    @NotNull
    private WorkLocationType workLocation;// 근무지

    @NotNull
    private EmploymentType employmentType;// 입사구분

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joiningDate;// 입사 날짜

    @NotNull
    private ResignationType resignationType; //퇴사 타입 (재직/퇴사/휴직)

    public EmployeeShortResponseDTO(Employee employee) {

        this.companyId = employee.getCompanyId();
        this.name = employee.getName();
        this.position = employee.getPosition();
        this.positionLank = employee.getPositionLank();
        this.workLocation = employee.getWorkLocation();
        this.employmentType = employee.getEmploymentType();
        this.joiningDate = employee.getJoiningDate();
        this.resignationType = employee.getResignationType();

        // 부서명 LAZY 로딩
        this.department = employee.getDepartment().getDepartmentName();
    }
}
