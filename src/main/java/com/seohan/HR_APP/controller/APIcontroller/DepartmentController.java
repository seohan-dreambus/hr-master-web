package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.dto.department.ReadDepartmentResponseDTO;
import com.seohan.HR_APP.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    //상위 부서 목록 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/departments/root")
    public ResponseDTO<?> getRootDepartmentList(){
        final List<Department> rootList = departmentService.getRootList();

        List<ReadDepartmentResponseDTO> result = rootList.stream()
                .map(department -> new ReadDepartmentResponseDTO(department))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "(root) 상위부서 리스트 조회 성공");
    }

    //상위 부서(입력값)의 하위 부서 목록 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/departments/{departmentChangeId}/{departmentCode}")
    public ResponseDTO<?> getSubDepartmentList(@PathVariable String departmentChangeId, @PathVariable String departmentCode){
        final List<Department> subList = departmentService.getSubList(departmentChangeId, departmentCode);

        List<ReadDepartmentResponseDTO> result = subList.stream()
                .map(department -> new ReadDepartmentResponseDTO(department))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "하위부서 리스트 조회 성공");
    }
}
