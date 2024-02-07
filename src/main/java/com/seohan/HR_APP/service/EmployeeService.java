package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.employee.UpdateEmployeeRequestDTO;
import com.seohan.HR_APP.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Employee create(CreateEmployeeRequestDTO requestDTO) {
        Employee employee = requestDTO.toEntity();
        //TODO 사번 생성
        employee.encodePassword(passwordEncoder);
        return repo.save(employee);
    }

    public Employee getEmployeeById(String companyId) {
        return repo.findByCompanyId(companyId)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public void delete(Employee findEmployee) {
        repo.delete(findEmployee);
    }

    @Transactional
    public Employee update(String companyId, UpdateEmployeeRequestDTO requestDTO) {
        Employee employee = repo.findByCompanyId(companyId)
                .orElseThrow(() -> new EntityNotFoundException());

        //employee 수정
        //TODO

        return employee;
    }
}
