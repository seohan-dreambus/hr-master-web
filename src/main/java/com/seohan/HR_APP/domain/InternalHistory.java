package com.seohan.HR_APP.domain;

import com.seohan.HR_APP.domain.enumType.PositionLankType;
import com.seohan.HR_APP.domain.enumType.PositionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "HR_INTERNAL_HISTORY_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InternalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Employee employee;

    @Column(name = "change_reason", nullable = false)
    private String changeReason;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "position", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PositionType position;

    @Column(name = "position_lank", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PositionLankType positionLank;

    @Column(name = "duties", nullable = false)
    private String duties; //담당업무

    @Column(name = "note")
    private String note;

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    @Builder
    public InternalHistory(Employee employee, String changeReason, LocalDateTime changeTime, String department, PositionType position, PositionLankType positionLank, String duties, String note) {
        setEmployee(employee);
        this.changeReason = changeReason;
        this.changeTime = changeTime;
        this.department = department;
        this.position = position;
        this.positionLank = positionLank;
        this.duties = duties;
        this.note = note;
    }
}
