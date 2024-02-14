package com.seohan.HR_APP.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seohan.HR_APP.domain.InternalHistory;
import com.seohan.HR_APP.domain.enumType.PositionLankType;
import com.seohan.HR_APP.domain.enumType.PositionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InternalHistoryResponseDTO {

    private String changeReason;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime changeTime;

    //TODO 근무지

    private String department;

    private PositionType position;

    private PositionLankType positionLank;

    private String duties; //담당업무

    private String note;

    public InternalHistoryResponseDTO(InternalHistory history) {
        this.changeReason = history.getChangeReason();
        this.changeTime = history.getChangeTime();
        this.department = history.getDepartment();
        this.position = history.getPosition();
        this.positionLank = history.getPositionLank();
        this.note = history.getNote();
        this.duties = history.getDuties();
    }
}
