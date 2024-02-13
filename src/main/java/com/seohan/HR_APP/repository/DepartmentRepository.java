package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Department.DepartmentId> {
    Optional<Department> findByDepartmentName(String departmentName);

    Optional<Department> findById(Department.DepartmentId departmentId);

    @Query("SELECT d FROM Department d where d.upperDepartment = null")
    List<Department> findRootDepartmentList();

//    @Query("SELECT d FROM Department d where d.id.departmentChangeId = :departmentChangeId and d.upperDepartment != null")
//    List<Department> findSubDepartmentList(String departmentChangeId);
}
