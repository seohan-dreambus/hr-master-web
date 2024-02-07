package com.seohan.HR_APP;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.enumType.ResignationType;
import com.seohan.HR_APP.repository.DepartmentRepository;
import com.seohan.HR_APP.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB(){
        initTestMember();
    }

    public void initTestMember() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Department department = Department.builder().
                departmentChangeId("1")
                .departmentCode("12")
                .departmentName("IT기획")
                .departmentEnglishName("IT Planner")
                .upperDepartmentCode(null)
                .build();

        Drepo.save(department);

        Employee employee = Employee.builder()
                .password(passwordEncoder.encode("1234"))
                .name("홍길동")
                .englishName("Hong Gildong") // 영어 이름 예시
                .personalNumber("123456-1234567") // 주민번호 예시
                .phoneNumber("010-1234-5678") // 휴대폰 번호 예시
                .isMilitary(true) // 군필 여부 예시
                .nationality("대한민국") // 국적 예시
                .homeAddress("서울시 강남구") // 실거주지 주소 예시
                .detailAddress("강남대로 1234번지") // 상세주소 예시
                .employmentType("정규직") // 입사구분 예시
                .joiningDate(new Date()) // 입사 날짜 예시 (현재 날짜)
                .resignationType(ResignationType.EMPLOYMENT) // 퇴사 타입 예시
                .workLocation("서울") // 근무지 예시
                .jobCategory("개발") // 직군 예시
                .position("개발자") // 직책 예시
                .positionLank("대리") // 직급 예시
                .build();
        employee.setDepartment(department);

        Erepo.save(employee);
    }
}
