package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByCompanyId(String companyId); //사번으로 단일 조회

    List<Employee> findAllByName(String name); //이름으로 목록 조회

    @Query("SELECT e FROM Employee e WHERE e.department.id.departmentChangeId = :departmentChangeId")
    List<Employee> findAllByDepartment(@Param("departmentChangeId")String departmentChangeId); //부서개편ID로 목록조회
}
