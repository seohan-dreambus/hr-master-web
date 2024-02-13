package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.domain.Department;
import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.TrainingHistory;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.dto.department.ReadDepartmentResponseDTO;
import com.seohan.HR_APP.dto.employee.CreateEmployeeRequestDTO;
import com.seohan.HR_APP.dto.history.CreateTrainingRequestDTO;
import com.seohan.HR_APP.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/history/training")
    public ResponseDTO<?> createTrainingHistory(@RequestBody @Valid final CreateTrainingRequestDTO requestDTO ){
        historyService.createTrainingHistory(requestDTO);
        return new ResponseDTO<>(null, "교육 이력 추가 성공");
    }
}
