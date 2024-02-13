package com.seohan.HR_APP.controller.APIcontroller;

import com.seohan.HR_APP.domain.InternalHistory;
import com.seohan.HR_APP.domain.RewardPenaltyHistory;
import com.seohan.HR_APP.domain.TrainingHistory;
import com.seohan.HR_APP.dto.ResponseDTO;
import com.seohan.HR_APP.dto.history.*;
import com.seohan.HR_APP.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HistoryController {

    private final HistoryService historyService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/history/internal")
    public ResponseDTO<?> createInternalHistory(@RequestBody @Valid final CreateInternalHistoryRequestDTO requestDTO ){
        historyService.createInternalHistory(requestDTO);
        return new ResponseDTO<>(null, "사내 이력 추가 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/history/internal/{companyId}")
    public ResponseDTO<?> getAllInternalHistoryListByEmployee(@PathVariable String companyId){
        final List<InternalHistory> historyList = historyService.getInternalHistoryList(companyId);
        List<InternalHistoryResponseDTO> result = historyList.stream()
                .map(history -> new InternalHistoryResponseDTO(history))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "사내 이력 목록 조회 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/history/training")
    public ResponseDTO<?> createTrainingHistory(@RequestBody @Valid final CreateTrainingRequestDTO requestDTO ){
        historyService.createTrainingHistory(requestDTO);
        return new ResponseDTO<>(null, "사내 이력 추가 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/history/training/{companyId}")
    public ResponseDTO<?> getAllTrainingHistoryListByEmployee(@PathVariable String companyId){
        final List<TrainingHistory> historyList = historyService.getTrainingHistoryList(companyId);
        List<TrainingHistoryResponseDTO> result = historyList.stream()
                .map(history -> new TrainingHistoryResponseDTO(history))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "사내 이력 목록 조회 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/history/rewardPenalty")
    public ResponseDTO<?> createRewardPenaltyHistory(@RequestBody @Valid final CreateRewardPenaltyRequestDTO requestDTO ){
        historyService.createRewardPenaltyHistory(requestDTO);
        return new ResponseDTO<>(null, "상벌 이력 추가 성공");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/history/rewardPenalty/{companyId}")
    public ResponseDTO<?> getAllRewardPenaltyHistoryListByEmployee(@PathVariable String companyId){
        final List<RewardPenaltyHistory> historyList = historyService.getRewardPenaltyHistoryList(companyId);
        List<RewardPenaltyHistoryResponseDTO> result = historyList.stream()
                .map(history -> new RewardPenaltyHistoryResponseDTO(history))
                .collect(Collectors.toList());

        return new ResponseDTO<>(result, "상벌 이력 목록 조회 성공");
    }
}
