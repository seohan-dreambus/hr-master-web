package com.seohan.HR_APP.dto.history;

import com.seohan.HR_APP.domain.RewardPenaltyHistory;
import com.seohan.HR_APP.domain.enumType.IssueGrade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RewardPenaltyHistoryResponseDTO {

    private Boolean issueType; //true:상, false:벌

    private LocalDate issueDate;

    private String issueContent;

    private IssueGrade issueGrade;

    private Integer resultsPoint;

    public RewardPenaltyHistoryResponseDTO(RewardPenaltyHistory history) {
        this.issueType = history.getIssueType();
        this.issueGrade = history.getIssueGrade();
        this.issueContent = history.getIssueContent();
        this.issueDate = history.getIssueDate();
        this.resultsPoint = history.getResultPoint();
    }
}
