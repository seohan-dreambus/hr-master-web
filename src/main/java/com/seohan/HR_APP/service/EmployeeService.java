package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.enumType.UserAuthority;
import com.seohan.HR_APP.dto.LoginRequestDTO;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.employee.UpdateEmployeeRequestDTO;
import com.seohan.HR_APP.repository.DepartmentRepository;
import com.seohan.HR_APP.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository repo;
    private final DepartmentRepository departmentRepo;
    private final PasswordEncoder passwordEncoder;

    public Employee login(LoginRequestDTO loginDTO) throws AuthException {
        Employee employee = repo.findById(loginDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        //ADMIN 만 로그인 가능
        if(employee.getUserAuthority() == UserAuthority.ADMIN){
            //비밀번호 일치 확인
            if(passwordEncoder.matches(loginDTO.getPassword(), employee.getPassword())) return employee;
            else throw new AuthException("사원번호와 비밀번호가 일치하지 않습니다.");
        }
        else throw new AuthException("권한이 없습니다.");
    }
    @Transactional
    public Employee create(CreateEmployeeRequestDTO requestDTO) {
        //부서 세팅
        Department department = departmentRepo.findByDepartmentName(requestDTO.getDepartment())
                .orElseThrow(() -> new EntityNotFoundException());
        Employee employee = requestDTO.toEntity(department);

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
    public Employee update(String companyId, UpdateEmployeeRequestDTO updateDTO) {
        Employee employee = repo.findByCompanyId(companyId)
                .orElseThrow(() -> new EntityNotFoundException());

        //employee 수정
        if (updateDTO.getName() != null) employee.updateName(updateDTO.getName());
        if (updateDTO.getEnglishName() != null) employee.updateEnglishName(updateDTO.getEnglishName());
        if (updateDTO.getPersonalNumber() != null) employee.updatePersonalNumber(updateDTO.getPersonalNumber());
        if (updateDTO.getPhoneNumber() != null) employee.updatePhoneNumber(updateDTO.getPhoneNumber());
        if (updateDTO.getHomePhoneNumber() != null) employee.updateHomePhoneNumber(updateDTO.getHomePhoneNumber());
        if (updateDTO.getIsMilitary() != null) employee.updateMilitary(updateDTO.getIsMilitary());
        if (updateDTO.getNationality() != null) employee.updateNationality(updateDTO.getNationality());
        if (updateDTO.getIsMartial() != null) employee.updateMartial(updateDTO.getIsMartial());
        if (updateDTO.getWeddingDay() != null) employee.updateWeddingDay(updateDTO.getWeddingDay());
        if (updateDTO.getHomeAddress() != null) employee.updateHomeAddress(updateDTO.getHomeAddress());
        if (updateDTO.getDetailAddress() != null) employee.updateDetailAddress(updateDTO.getDetailAddress());
        if (updateDTO.getEmploymentType() != null) employee.updateEmploymentType(updateDTO.getEmploymentType());
        if (updateDTO.getJoiningDate() != null) employee.updateJoiningDate(updateDTO.getJoiningDate());
        if (updateDTO.getResignationType() != null) employee.updateResignationType(updateDTO.getResignationType());
        if (updateDTO.getResignationDate() != null) employee.updateResignationDate(updateDTO.getResignationDate());
        if (updateDTO.getResignationReason() != null) employee.updateResignationReason(updateDTO.getResignationReason());
        if (updateDTO.getResignationAmount() != null) employee.updateResignationAmount(updateDTO.getResignationAmount());
        if (updateDTO.getWorkLocation() != null) employee.updateWorkLocation(updateDTO.getWorkLocation());
        if (updateDTO.getPosition() != null) employee.updatePosition(updateDTO.getPosition());
        if (updateDTO.getPositionLank() != null) employee.updatePositionLank(updateDTO.getPositionLank());
        if (updateDTO.getInternalPhone() != null) employee.updateInternalPhone(updateDTO.getInternalPhone());
        if (updateDTO.getInternalEmail() != null) employee.updateInternalEmail(updateDTO.getInternalEmail());
        if (updateDTO.getIsHighPerformance() != null) employee.updateHighPerformance(updateDTO.getIsHighPerformance());
        if (updateDTO.getIsUnionMember() != null) employee.updateUnionMember(updateDTO.getIsUnionMember());
        if (updateDTO.getIsOverseasAssignment() != null) employee.updateOverseasAssignment(updateDTO.getIsOverseasAssignment());

        //부서 수정
        if (updateDTO.getDepartment() != null){
            Department department = departmentRepo.findByDepartmentName(updateDTO.getDepartment())
                            .orElseThrow(() -> new EntityNotFoundException());
            employee.setDepartment(department);
        }
        return employee;
    }

    public List<Employee> getEmployeeByName(String name) {
        return repo.findAllByName(name);
    }

    public List<Employee> getEmployeeByDepartment(String departmentChangeId) {
        return repo.findAllByDepartment(departmentChangeId);
    }

    public List<Employee> getEmployeeByUpperDepartment(String departmentCode) {
        return repo.findAllByUpperDepartment(departmentCode);
    }
}
