package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.TrainingHistory;
import com.seohan.HR_APP.dto.history.CreateTrainingRequestDTO;
import com.seohan.HR_APP.repository.EmployeeRepository;
import com.seohan.HR_APP.repository.TrainingHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final EmployeeRepository employeeRepo;
    private final TrainingHistoryRepository trainingRepo;

    @Transactional
    public TrainingHistory createTrainingHistory(CreateTrainingRequestDTO requestDTO){
        Employee employee = employeeRepo.findById(requestDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("이력 생성 대상자를 찾을 수 없습니다."));
        TrainingHistory trainingHistory = requestDTO.toEntity(employee);
        return trainingRepo.save(trainingHistory);
    }

    public List<TrainingHistory> getTrainingHistoryList(String companyId){
        return trainingRepo.findAllByCompanyId(companyId);
    }
}
