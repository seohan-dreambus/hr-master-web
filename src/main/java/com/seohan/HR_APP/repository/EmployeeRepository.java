package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByCompanyId(String companyId); //사번으로 단일 조회

    List<Employee> findByName(String name); //이름으로 목록 조회
}
