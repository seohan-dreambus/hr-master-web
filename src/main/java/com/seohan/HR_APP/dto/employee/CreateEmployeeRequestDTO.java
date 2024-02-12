package com.seohan.HR_APP.dto.employee;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.enumType.ResignationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEmployeeRequestDTO {
    //일단 데이터 전부 넣어놨는데
    //일부 데이터만 입력가능하게 할거면 Not null 인거 제외하거 빼겠음

    @NotBlank
    private String password; //비밀번호

    // 인적사항
    @NotBlank
    private String name; //이름

    @NotBlank
    private String englishName; //영어이름

    @NotBlank
    private String personalNumber; // 주민번호

    @NotBlank
    private String phoneNumber; //휴대폰 번호

    private String homePhoneNumber;//자택 전화번호(비상연락망)

    @NotNull
    private Boolean isMilitary; //군필

    @NotBlank
    private String nationality; //국적

    private Boolean isMartial;// 결혼 유무

    private Date weddingDay;// 결혼 기념일

    @NotBlank
    private String homeAddress;// 실거주지 주소

    @NotBlank
    private String detailAddress;// 상세주소

    //회사 근무사항
    @NotBlank
    private String employmentType;// 입사구분

    @NotNull
    private Date joiningDate;// 입사 날짜

    @NotNull
    private ResignationType resignationType; //퇴사 타입 (재직/퇴사/휴직)

    private Date resignationDate;// 퇴사 일자

    private String resignationReason;// 퇴사 사유

    private String resignationAmount;// 퇴사 금액

    @NotBlank
    private String workLocation;// 근무지

    @NotBlank
    private String jobCategory;// 직군

    @NotBlank
    private String department;// 부서

    @NotBlank
    private String position;// 직책

    @NotBlank
    private String positionLank;// 직급

    private String internalPhone;// 사내전화

    private String internalEmail;// 사내메일

    @NotNull
    private Boolean isHighPerformance;// 고과여부

    @NotNull
    private Boolean isUnionMember;// 조합원 여부

    @NotNull
    private Boolean isOverseasAssignment;// 해외파견 여부

    private String shift;// 근무조

    public Employee toEntity(Department department) {
        return Employee.builder()
                .department(department)
                .password(password)
                .name(name)
                .englishName(englishName)
                .personalNumber(personalNumber)
                .phoneNumber(phoneNumber)
                .homePhoneNumber(homePhoneNumber)
                .isMilitary(isMilitary)
                .nationality(nationality)
                .isMartial(isMartial)
                .weddingDay(weddingDay)
                .homeAddress(homeAddress)
                .detailAddress(detailAddress)
                .employmentType(employmentType)
                .joiningDate(joiningDate)
                .resignationType(resignationType)
                .resignationDate(resignationDate)
                .resignationReason(resignationReason)
                .resignationAmount(resignationAmount)
                .workLocation(workLocation)
                .jobCategory(jobCategory)
                .position(position)
                .positionLank(positionLank)
                .internalPhone(internalPhone)
                .internalEmail(internalEmail)
                .isHighPerformance(isHighPerformance)
                .isUnionMember(isUnionMember)
                .isOverseasAssignment(isOverseasAssignment)
                .shift(shift)
                .build();
    }
}
