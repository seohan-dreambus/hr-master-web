package com.seohan.HR_APP;

import com.seohan.HR_APP.domain.*;
import com.seohan.HR_APP.domain.enumType.*;
import com.seohan.HR_APP.dto.history.CreateRewardPenaltyRequestDTO;
import com.seohan.HR_APP.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @PostConstruct는 해당 빈의 AOP 적용을 보장하지 않으므로 우회해야한다.
 * 애플리케이션 컨텍스트가 완전히 초기화 된 이벤트를 받아서 호출하는 방법을 사용
 */
@Profile("dev")
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final DepartmentRepository Drepo;
    private final EmployeeRepository Erepo;
    private final JdbcTemplateDepartmentRepository JdbcDrepo;
    private final TrainingHistoryRepository THrepo;
    private final InternalHistoryRepository IHrepo;
    private final RewardPenaltyHistoryRepository RPHrepo;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB(){
        initTestMember();
    }

    public void initTestMember() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        JdbcDrepo.insertUpperDepartment("202402121", "D20ITM", "IT Planning", "IT기획");
        JdbcDrepo.insertSubDepartment("202402121","D20ITM", "202402122", "D20ITM", "IT Planning Team1", "IT기획 1팀");
        JdbcDrepo.insertSubDepartment("202402121","D20ITM", "202402123", "D20ITM", "IT Planning Team2", "IT기획 2팀");
        JdbcDrepo.insertSubDepartment("202402121","D20ITM", "202402124", "D20ITM", "IT Planning Team3", "IT기획 3팀");

//        Department.DepartmentId id = new Department.DepartmentId("202402121","D20ITM");
//        Department upperDepartment = Department.builder()
//                .departmentId(id)
//                .departmentName("IT기획")
//                .departmentEnglishName("IT Planner")
//                .upperDepartment(null)
//                .isLeafNode(false)
//                .build();
//
//        Department.DepartmentId id2 = new Department.DepartmentId("202402122","D20ITM");
//        Department subDepartment = Department.builder()
//                .departmentId(id2)
//                .departmentName("IT기획 1팀")
//                .departmentEnglishName("IT Planner team 1")
//                .upperDepartment(upperDepartment)
//                .isLeafNode(true)
//                .build();
//
//        Drepo.save(upperDepartment);
//        Drepo.save(subDepartment);

        Employee employee = Employee.builder()
                .password(passwordEncoder.encode("1234"))
                .name("홍길동")
                .englishName("Hong Gildong") // 영어 이름 예시
                .personalNumber("123456-1234567") // 주민번호 예시
                .phoneNumber("010-1234-5678") // 휴대폰 번호 예시
                .isMilitary(true) // 군필 여부 예시
                .nationality(NationalityType.대한민국) // 국적 예시
                .homeAddress("서울시 강남구") // 실거주지 주소 예시
                .detailAddress("강남대로 1234번지") // 상세주소 예시
                .employmentType(EmploymentType.신입) // 입사구분 예시
                .joiningDate(new Date()) // 입사 날짜 예시 (현재 날짜)
                .resignationType(ResignationType.재직) // 퇴사 타입 예시
                .workLocation(WorkLocationType.서울) // 근무지 예시
                .position(PositionType.매니저) // 직책 예시
                .positionLank(PositionLankType.사원) // 직급 예시
                .shift(ShiftType.주간)
                .build();

        Department.DepartmentId id = new Department.DepartmentId("202402122", "D20ITM");
        Department department = Drepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        employee.setDepartment(department);

        Erepo.save(employee);

        TrainingHistory th = TrainingHistory.builder()
                .trainingName("이메일 피싱 보안 교육1")
                .trainingContent("교육내용")
                .trainingDate(new Date())
                .trainingType(TrainingType.사내)
                .employee(employee)
                .build();

        TrainingHistory th2 = TrainingHistory.builder()
                .trainingName("이메일 피싱 보안 교육2")
                .trainingContent("교육내용")
                .trainingDate(new Date())
                .trainingType(TrainingType.사내)
                .employee(employee)
                .build();

        THrepo.save(th);
        THrepo.save(th2);



        InternalHistory ih = InternalHistory.builder()
                .changeReason("승진")
                .changeTime(LocalDateTime.parse("2023-02-01T10:30:00"))
                .department("IT기획")
                .position(PositionType.책임매니저)
                .positionLank(PositionLankType.대리)
                .duties("기획 및 프로젝트 관리")
                .note("승진 사유는 고과 반영 및 인사 평가에 따른 것입니다.")
                .employee(employee)
                .build();

        InternalHistory ih2 = InternalHistory.builder()
                .changeReason("부서 이동")
                .changeTime(LocalDateTime.parse("2024-02-01T10:30:00"))
                .department("IT운영")
                .position(PositionType.책임매니저)
                .positionLank(PositionLankType.대리)
                .duties("기획 및 프로젝트 관리")
                .note("이동 사유는 전략적인 부서 조직 변경에 따른 것입니다.")
                .employee(employee)
                .build();

        IHrepo.save(ih);
        IHrepo.save(ih2);

        RewardPenaltyHistory rph = RewardPenaltyHistory.builder()
                .issueType(true)
                .issueDate(LocalDate.parse("2024-02-11"))
                .issueContent("성과 우수")
                .issueGrade(IssueGrade.SILVER)
                .resultPoint(3)
                .employee(employee)
                .build();

        RewardPenaltyHistory rph2 = RewardPenaltyHistory.builder()
                .issueType(true)
                .issueDate(LocalDate.parse("2024-02-11"))
                .issueContent("성과 우수")
                .issueGrade(IssueGrade.GOLD)
                .resultPoint(8) //3+5
                .employee(employee)
                .build();

        RPHrepo.save(rph);
        RPHrepo.save(rph2);


    }
}
