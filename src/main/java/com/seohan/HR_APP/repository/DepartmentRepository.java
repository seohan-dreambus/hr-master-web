package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findByDepartmentName(String Id); //PK 단일 조회
}
