package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {

    @Query("SELECT th FROM TrainingHistory th WHERE th.employee.companyId = :companyId")
    List<TrainingHistory> findAllByCompanyId(@Param("companyId") String companyId);
}
