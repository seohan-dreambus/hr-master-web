package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.RewardPenaltyHistory;
import com.seohan.HR_APP.domain.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RewardPenaltyHistoryRepository extends JpaRepository<RewardPenaltyHistory, Long> {
    @Query("SELECT rp FROM RewardPenaltyHistory rp WHERE rp.employee.companyId = :companyId")
    List<RewardPenaltyHistory> findAllByCompanyId(@Param("companyId") String companyId);

    @Query("SELECT rp FROM RewardPenaltyHistory rp WHERE rp.employee.companyId = :companyId ORDER BY rp.issueDate DESC")
    RewardPenaltyHistory findLastByCompanyId(@Param("companyId") String companyId);
}
