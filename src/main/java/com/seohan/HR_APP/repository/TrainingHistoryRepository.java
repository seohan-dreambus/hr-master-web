package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {
}
