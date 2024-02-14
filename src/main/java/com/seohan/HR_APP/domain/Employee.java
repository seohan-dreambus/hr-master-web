package com.seohan.HR_APP.domain;

import com.seohan.HR_APP.domain.enumType.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "HR_INFO_TB", uniqueConstraints =
        {@UniqueConstraint(name = "UNIQUE_KEY", columnNames = {"personal_number","phone_number"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Slf4j
public class Employee extends TimeAuditing {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "department_change_id", referencedColumnName = "department_change_id"),
            @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    })
    private Department department; // 부서

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TrainingHistory> trainingList = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<RewardPenaltyHistory> RewardPenaltyList = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<InternalHistory> internalHistoryList = new ArrayList<>();

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
    @Enumerated(value = EnumType.STRING)
    private NationalityType nationality; //국적

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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType employmentType;// 입사구분

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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "work_location")
    private WorkLocationType workLocation;// 근무지

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private PositionType position;// 직책

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "position_lank")
    private PositionLankType positionLank;// 직급

    @Column(name = "internal_phone")
    private String internalPhone;// 사내전화

    @Column(name = "internal_email")
    private String internalEmail;// 사내메일

    @NotNull
    @Column(name = "is_high_performance")
    private Boolean isHighPerformance;// 고과여부

    @NotNull
    @Column(name = "is_union_member")
    private Boolean isUnionMember;// 조합원 여부

    @NotNull
    @Column(name = "is_overseas_assignment")
    private Boolean isOverseasAssignment;// 해외파견 여부

    private ShiftType shift;// 근무조

    public void encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
    }

    //생성자
    @Builder
    public Employee(Department department, String password, String name, String englishName, String personalNumber, String phoneNumber, String homePhoneNumber, Boolean isMilitary, NationalityType nationality, Boolean isMartial, Date weddingDay, String homeAddress, String detailAddress, EmploymentType employmentType, Date joiningDate, ResignationType resignationType, Date resignationDate, String resignationReason, String resignationAmount, WorkLocationType workLocation, PositionType position, PositionLankType positionLank, String internalPhone, String internalEmail, Boolean isHighPerformance, Boolean isUnionMember, Boolean isOverseasAssignment, ShiftType shift) {
        this.companyId = createCompanyId();
        this.userAuthority = UserAuthority.ADMIN;
        setDepartment(department);

        this.name = name;
        this.password = password;
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
        //년도월 + 랜덤문자열4자리
        LocalDate currentDate = LocalDate.now();
        String formattedYear = String.format("%02d", currentDate.getYear() % 100);
        String formattedMonth = String.format("%02d", currentDate.getMonthValue());

        // 결과 문자열 생성
        String randomStr = java.util.UUID.randomUUID().toString().substring(0,4);
        String result = formattedYear + formattedMonth + randomStr;

        return result;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //==수정 메서드==//

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void updatePersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void updateMilitary(Boolean military) {
        isMilitary = military;
    }

    public void updateNationality(NationalityType nationality) {
        this.nationality = nationality;
    }

    public void updateMartial(Boolean martial) {
        isMartial = martial;
    }

    public void updateWeddingDay(Date weddingDay) {
        this.weddingDay = weddingDay;
    }

    public void updateHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void updateDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void updateEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public void updateJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void updateResignationType(ResignationType resignationType) {
        this.resignationType = resignationType;
    }

    public void updateResignationDate(Date resignationDate) {
        this.resignationDate = resignationDate;
    }

    public void updateResignationReason(String resignationReason) {
        this.resignationReason = resignationReason;
    }

    public void updateResignationAmount(String resignationAmount) {
        this.resignationAmount = resignationAmount;
    }

    public void updateWorkLocation(WorkLocationType workLocation) {
        this.workLocation = workLocation;
    }

    public void updatePosition(PositionType position) {
        this.position = position;
    }

    public void updatePositionLank(PositionLankType positionLank) {
        this.positionLank = positionLank;
    }

    public void updateInternalPhone(String internalPhone) {
        this.internalPhone = internalPhone;
    }

    public void updateInternalEmail(String internalEmail) {
        this.internalEmail = internalEmail;
    }

    public void updateHighPerformance(Boolean highPerformance) {
        isHighPerformance = highPerformance;
    }

    public void updateUnionMember(Boolean unionMember) {
        isUnionMember = unionMember;
    }

    public void updateOverseasAssignment(Boolean overseasAssignment) {
        isOverseasAssignment = overseasAssignment;
    }

    public void updateShift(ShiftType shift) {
        this.shift = shift;
    }
}
