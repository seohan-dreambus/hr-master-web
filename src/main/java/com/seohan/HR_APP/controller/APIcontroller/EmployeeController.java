package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.dto.employee.*;
import com.seohan.HR_APP.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/api/employees")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid final CreateEmployeeRequestDTO requestDTO, UriComponentsBuilder uriBuilder){

        final Employee createdEmployee = employeeService.create(requestDTO);
        URI location = uriBuilder.path("/api/employees/{companyId}")
                .buildAndExpand(createdEmployee.getCompanyId()).toUri();

        return ResponseEntity.created(location)
                .body(new ResponseDTO<>(null, "사원 등록 성공"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/employees/{companyId}")
    public ResponseDTO<?> deleteEmployee(@PathVariable String companyId){

        Employee findEmployee = employeeService.getEmployeeById(companyId);
        employeeService.delete(findEmployee);
        return new ResponseDTO<>(null, "사원 삭제 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/employees/{companyId}")
    public ResponseDTO<ReadEmployeeResponseDTO> modifyEmployee(
            @PathVariable String companyId,
            @RequestBody @Valid final UpdateEmployeeRequestDTO requestDTO){

        final Employee updatedEmployee = employeeService.update(companyId, requestDTO);
        return new ResponseDTO<>(new ReadEmployeeResponseDTO(updatedEmployee), "사원 수정 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/employees/{companyId}")
    public ResponseDTO<?> getEmployeeInfo(@PathVariable String companyId){

        log.info(companyId);
        final Employee findEmployee = employeeService.getEmployeeById(companyId);
        return new ResponseDTO<>(new ReadEmployeeResponseDTO(findEmployee), "사원 조회 성공");
    }

    //사원 이름으로 목록 조회(검색)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/employees/name/{name}")
    public ResponseDTO<?> getEmployeeListByName(@PathVariable String name){
        final List<Employee> employeeList = employeeService.getEmployeeByName(name);
        List<EmployeeShortResponseDTO> result = employeeList.stream()
                .map(employee -> new EmployeeShortResponseDTO(employee))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "사원 이름으로 사원 목록 조회 성공");
    }

    //부서로 목록 조회(검색)
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/api/employees/department/{departmentChangeId}")
//    public ResponseDTO<?> getEmployeeListByDepartment(@PathVariable String departmentChangeId){
//        final List<Employee> employeeList = employeeService.getEmployeeByDepartment(departmentChangeId);
//        List<EmployeeShortResponseDTO> result = employeeList.stream()
//                .map(employee -> new EmployeeShortResponseDTO(employee))
//                .collect(Collectors.toList());
//
//        return new ResponseDTO<>(result, "부서별 사원 목록 조회 성공");
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/employees/department/{departmentCode}")
    public ResponseDTO<?> getEmployeeListByUpperDepartment(@PathVariable String departmentCode){
        final List<Employee> employeeList = employeeService.getEmployeeByUpperDepartment(departmentCode);
        List<EmployeeShortResponseDTO> result = employeeList.stream()
                .map(employee -> new EmployeeShortResponseDTO(employee))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "상위부서별 사원 목록 조회 성공");
    }
}