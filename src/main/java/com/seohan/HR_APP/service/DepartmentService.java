package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository repo;

    public List<Department> getRootList() {
        //상위 부서 조회
        return repo.findRootDepartmentList();
    }

    public List<Department> getSubList(String upperDepartmentId, String upperDepartmentCode) {//id?code?
//        return repo.findSubDepartmentList(upperDepartmentId);
        Department department = repo.findById(new Department.DepartmentId(upperDepartmentId, upperDepartmentCode))
                .orElseThrow(() -> new EntityNotFoundException());

        return department.getSubDepartmentList();
    }

}
