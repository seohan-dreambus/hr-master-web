package com.seohan.HR_APP.dto.history;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.RewardPenaltyHistory;
import com.seohan.HR_APP.domain.enumType.IssueGrade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRewardPenaltyRequestDTO {

    @NotBlank
    private String companyId; //이력 대상자 사번

    @NotNull
    private Boolean issueType; //true:상, false:벌

    @NotNull
    private LocalDate issueDate;

    private String issueContent;

    @NotNull
    private IssueGrade issueGrade;

    public String getCompanyId() {
        return companyId;
    }

    public RewardPenaltyHistory toEntity(Employee employee) {
        return RewardPenaltyHistory.builder()
                .employee(employee)
                .issueType(issueType)
                .issueDate(issueDate)
                .issueContent(issueContent)
                .issueGrade(issueGrade)
                .build();
    }
}
