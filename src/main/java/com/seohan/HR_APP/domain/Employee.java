package com.seohan.HR_APP.domain;

import com.seohan.HR_APP.domain.enumType.ResignationType;
import com.seohan.HR_APP.domain.enumType.UserAuthority;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@Table(name = "HR_INFO_TB", uniqueConstraints =
        {@UniqueConstraint(name = "UNIQUE_KEY", columnNames = {"personal_number","phone_number"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class Employee {

    @Id
    @Column(name = "company_id")
    private String companyId; //auto import 아님

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_authority")
    private UserAuthority userAuthority; // 권한

    @NotNull
    private String password; //비밀번호

    // 인적사항
    @NotNull
    @Column(name = "name")
    private String name; //이름

    @NotNull
    @Column(name = "english_name")
    private String englishName; //영어이름

    @NotNull
    @Column(name = "personal_number")
    private String personalNumber; // 주민번호

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber; //휴대폰 번호

    @Column(name = "home_phone_number")
    private String homePhoneNumber;//자택 전화번호(비상연락망)

    @NotNull
    @Column(name = "is_military")
    private Boolean isMilitary; //군필

    @NotNull
    private String nationality; //국적

    @Column(name = "is_martial")
    private Boolean isMartial;// 결혼 유무

    @Column(name = "wedding_day")
    private Date weddingDay;// 결혼 기념일

    @NotNull
    @Column(name = "home_address")
    private String homeAddress;// 실거주지 주소

    @NotNull
    @Column(name = "detail_address")
    private String detailAddress;// 상세주소

    //회사 근무사항
    @NotNull
    @Column(name = "employment_type")
    private String employmentType;// 입사구분

    @NotNull
    @Column(name = "joining_date")
    private Date joiningDate;// 입사 날짜

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status")
    private ResignationType resignationType; //퇴사 타입 (재직/퇴사/휴직)

    @Column(name = "resignation_date")
    private Date resignationDate;// 퇴사 일자

    @Column(name = "resignation_reason")
    private String resignationReason;// 퇴사 사유

    @Column(name = "resignation_amount")
    private String resignationAmount;// 퇴사 금액

    @NotNull
    @Column(name = "work_location")
    private String workLocation;// 근무지

    @NotNull
    @Column(name = "job_category")
    private String jobCategory;// 직군

    @NotNull
    private String department;// 부서

    @NotNull
    private String position;// 직책

    @NotNull
    @Column(name = "position_lank")
    private String positionLank;// 직급

    @Column(name = "internal_phone")
    private String internalPhone;// 사내전화

    @Column(name = "internal_email")
    private String internalEmail;// 사내메일

    @NotNull
    @Column(name = "is_high_performance")
    private String isHighPerformance;// 고과여부

    @NotNull
    @Column(name = "is_union_member")
    private String isUnionMember;// 조합원 여부

    @NotNull
    @Column(name = "is_overseas_assignment")
    private String isOverseasAssignment;// 해외파견 여부

    private String shift;// 근무조

    public void encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
    }

    //생성자
    @Builder
    public Employee(String password, String name, String englishName, String personalNumber, String phoneNumber, String homePhoneNumber, Boolean isMilitary, String nationality, Boolean isMartial, Date weddingDay, String homeAddress, String detailAddress, String employmentType, Date joiningDate, ResignationType resignationType, Date resignationDate, String resignationReason, String resignationAmount, String workLocation, String jobCategory, String department, String position, String positionLank, String internalPhone, String internalEmail, String isHighPerformance, String isUnionMember, String isOverseasAssignment, String shift) {
        this.companyId = createCompanyId();
        this.userAuthority = UserAuthority.ADMIN;

        this.name = name;
        this.englishName = englishName;
        this.personalNumber = personalNumber;
        this.phoneNumber = phoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.isMilitary = isMilitary;
        this.nationality = nationality;
        this.isMartial = isMartial;
        this.weddingDay = weddingDay;
        this.homeAddress = homeAddress;
        this.detailAddress = detailAddress;
        this.employmentType = employmentType;
        this.joiningDate = joiningDate;
        this.resignationType = resignationType;
        this.resignationDate = resignationDate;
        this.resignationReason = resignationReason;
        this.resignationAmount = resignationAmount;
        this.workLocation = workLocation;
        this.jobCategory = jobCategory;
        this.department = department;
        this.position = position;
        this.positionLank = positionLank;
        this.internalPhone = internalPhone;
        this.internalEmail = internalEmail;
        this.isHighPerformance = isHighPerformance;
        this.isUnionMember = isUnionMember;
        this.isOverseasAssignment = isOverseasAssignment;
        this.shift = shift;
    }

    private String createCompanyId() {
        //년도월+채번 24021, 24022, 24023
        LocalDate currentDate = LocalDate.now();
        String formattedYear = String.format("%02d", currentDate.getYear() % 100);
        String formattedMonth = String.format("%02d", currentDate.getMonthValue());

        // 결과 문자열 생성
        String result = formattedYear + formattedMonth;

        return result;
    }


    //==수정 메서드==//

}
