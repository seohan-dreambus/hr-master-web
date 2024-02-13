package com.seohan.HR_APP.service;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.InternalHistory;
import com.seohan.HR_APP.domain.RewardPenaltyHistory;
import com.seohan.HR_APP.domain.TrainingHistory;
import com.seohan.HR_APP.dto.history.CreateInternalHistoryRequestDTO;
import com.seohan.HR_APP.dto.history.CreateRewardPenaltyRequestDTO;
import com.seohan.HR_APP.dto.history.CreateTrainingRequestDTO;
import com.seohan.HR_APP.repository.EmployeeRepository;
import com.seohan.HR_APP.repository.InternalHistoryRepository;
import com.seohan.HR_APP.repository.RewardPenaltyHistoryRepository;
import com.seohan.HR_APP.repository.TrainingHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final EmployeeRepository employeeRepo;
    private final TrainingHistoryRepository trainingRepo;
    private final RewardPenaltyHistoryRepository RPHrepo;
    private final InternalHistoryRepository internalRepo;

    @Transactional
    public InternalHistory createInternalHistory(CreateInternalHistoryRequestDTO requestDTO){
        Employee employee = employeeRepo.findById(requestDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("이력 생성 대상자를 찾을 수 없습니다."));
        InternalHistory internalHistory = requestDTO.toEntity(employee);
        return internalRepo.save(internalHistory);
    }

    public List<InternalHistory> getInternalHistoryList(String companyId){
        return internalRepo.findAllByCompanyId(companyId);
    }

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

    @Transactional
    public RewardPenaltyHistory createRewardPenaltyHistory(CreateRewardPenaltyRequestDTO requestDTO){
        Employee employee = employeeRepo.findById(requestDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("이력 생성 대상자를 찾을 수 없습니다."));
        RewardPenaltyHistory history = requestDTO.toEntity(employee);

        //최신 이력의 마지막점수(0)에서 requestDTO의 Grade 점수에 따라 다시 계산해서 집어넣음
        RewardPenaltyHistory lastHistory = RPHrepo.findLastByCompanyId(requestDTO.getCompanyId());
        if(lastHistory == null) history.calculateResultPoint(0);
        else history.calculateResultPoint(lastHistory.getResultPoint());

        return RPHrepo.save(history);
    }

    public List<RewardPenaltyHistory> getRewardPenaltyHistoryList(String companyId){
        return RPHrepo.findAllByCompanyId(companyId);
    }
}
