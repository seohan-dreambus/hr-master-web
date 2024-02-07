package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.employee.ReadEmployeeListRequestDTO;
import com.seohan.HR_APP.dto.employee.ReadEmployeeResponseDTO;
import com.seohan.HR_APP.dto.employee.UpdateEmployeeRequestDTO;
import com.seohan.HR_APP.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/api/employees")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid final CreateEmployeeRequestDTO requestDTO, UriComponentsBuilder uriBuilder){

        final Employee createdEmployee = employeeService.create(requestDTO);
        URI location = uriBuilder.path("/user/{id}")
                .buildAndExpand(createdEmployee.getCompanyId()).toUri();

        return ResponseEntity.created(location)
                .body(new ResponseDTO<>(null, "사원 등록 성공"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/employees/{id}")
    public ResponseDTO<?> deleteEmployee(@PathVariable Long id){

        Employee findEmployee = employeeService.getEmployeeById(id);
        employeeService.changeToResignation(findEmployee);
        return new ResponseDTO<>(null, "사원 퇴사처리 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/employees/{id}")
    public ResponseDTO<ReadEmployeeResponseDTO> modifyUser(
            @PathVariable Long id,
            @RequestBody @Valid final UpdateEmployeeRequestDTO requestDTO){

        final Employee updatedEmployee = employeeService.update(id, requestDTO);
        return new ResponseDTO<>(new ReadEmployeeResponseDTO(updatedEmployee), "정상 수정 처리 되었습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/employees/{id}")
    public ResponseDTO<?> getUserInfo(@PathVariable Long id){

        final Employee findEmployee = employeeService.getEmployeeById(id);
        return new ResponseDTO<>(new ReadEmployeeResponseDTO(findEmployee), "사원 조회 성공");
    }

    //사원 목록 조회(검색)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/employees/{search}")
    public ResponseDTO<?> getUserList(
            @PathVariable String search,
            @RequestBody @Valid final ReadEmployeeListRequestDTO requestDTO){
        //TODO

        return null;
    }
}