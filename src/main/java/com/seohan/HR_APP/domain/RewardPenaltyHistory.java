package com.seohan.HR_APP.domain;


import com.seohan.HR_APP.domain.enumType.IssueGrade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "HR_REWARD_PENALTY_HISTORY_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RewardPenaltyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Employee employee;

    @NotNull
    @Column(name = "issue_type", nullable = false)
    private Boolean issueType; //true:상, false:벌

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "issue_content")
    private String issueContent;

    @Column(name = "issue_grade", nullable = false, length = 20)
    private IssueGrade issueGrade;

    @Column(name = "result_point", nullable = false)
    private int resultPoint;
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public void calculateResultPoint(int sum){
        this.resultPoint = sum + getPointByGrade();
    }

    public int getPointByGrade(){
        switch (this.issueGrade) {
            case GOLD: return 3;
            case SILVER: return 2;
            case BRONZE: return 1;
            case WARNING: return -1;
            case PENALTY: return -2;
            default: return 0; // 기본값 처리
        }
    }

    @Builder
    public RewardPenaltyHistory(Employee employee, Boolean issueType, LocalDate issueDate, String issueContent, IssueGrade issueGrade, int resultPoint) {
        setEmployee(employee);
        this.issueType = issueType;
        this.issueDate = issueDate;
        this.issueContent = issueContent;
        this.issueGrade = issueGrade;
        this.resultPoint = resultPoint;
    }
}
