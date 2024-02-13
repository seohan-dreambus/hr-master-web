package com.seohan.HR_APP.domain;

import com.seohan.HR_APP.domain.enumType.TrainingType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Entity
@Table(name = "HR_TRAINING_HISTORY_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TrainingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long trainingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Employee employee;

    @Column(name = "traning_type", nullable = false, length = 20, columnDefinition = "varchar(20) default '사내'")
    @Enumerated(value = EnumType.STRING)
    private TrainingType trainingType;

    @Column(name = "traning_date", nullable = false)
    private Date trainingDate;

    @Column(name = "traning_name", nullable = false, length = 50)
    private String trainingName;

    @Column(name = "traning_content", length = 255)
    private String trainingContent;

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    @Builder
    public TrainingHistory(Employee employee, TrainingType trainingType, Date trainingDate, String trainingName, String trainingContent) {
        setEmployee(employee);
        this.trainingType = trainingType;
        this.trainingDate = trainingDate;
        this.trainingName = trainingName;
        this.trainingContent = trainingContent;
    }
}
