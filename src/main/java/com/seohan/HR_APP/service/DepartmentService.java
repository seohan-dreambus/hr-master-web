package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.employee.UpdateEmployeeRequestDTO;
import com.seohan.HR_APP.repository.DepartmentRepository;
import com.seohan.HR_APP.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository repo;

    public Department getRootList() {
        return null;
    }

    public Department getLowerList(String upperDepartmentId) {//id?code?
        return null;
    }

}
