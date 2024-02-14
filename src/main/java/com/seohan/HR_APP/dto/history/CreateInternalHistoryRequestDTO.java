package com.seohan.HR_APP.dto.history;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.InternalHistory;
import com.seohan.HR_APP.domain.enumType.PositionLankType;
import com.seohan.HR_APP.domain.enumType.PositionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInternalHistoryRequestDTO {

    @NotBlank
    private String companyId; //이력 대상자 사번

    @NotBlank
    private String changeReason;

    @NotNull
    private LocalDateTime changeTime;

    //TODO 근무지

    @NotBlank
    private String department;

    @NotNull
    private PositionType position;

    @NotNull
    private PositionLankType positionLank;

    @NotBlank
    private String duties; //담당업무

    private String note;

    public InternalHistory toEntity(Employee employee) {
        return InternalHistory.builder()
                .employee(employee)
                .changeTime(changeTime)
                .changeReason(changeReason)
                .department(department)
                .position(position)
                .positionLank(positionLank)
                .duties(duties)
                .note(note)
                .build();
    }
}
