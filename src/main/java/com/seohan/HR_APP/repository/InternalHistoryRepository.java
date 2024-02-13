package com.seohan.HR_APP.repository;

import com.seohan.HR_APP.domain.InternalHistory;
import com.seohan.HR_APP.domain.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternalHistoryRepository extends JpaRepository<InternalHistory, Long> {
    @Query("SELECT ih FROM InternalHistory ih WHERE ih.employee.companyId = :companyId")
    List<InternalHistory> findAllByCompanyId(@Param("companyId") String companyId);
}
