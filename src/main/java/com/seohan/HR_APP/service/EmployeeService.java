package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.employee.UpdateEmployeeRequestDTO;
import com.seohan.HR_APP.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

//    private final EmployeeRepository repo;

    @Transactional
    public Employee create(CreateEmployeeRequestDTO requestDTO) {
        return null;
    }

    public Employee getEmployeeById(Long id) {
        return null;
    }

    @Transactional
    public void changeToResignation(Employee findEmployee) {
        return;
    }

    @Transactional
    public Employee update(Long id, UpdateEmployeeRequestDTO requestDTO) {
        return null;
    }
}
