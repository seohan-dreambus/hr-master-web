package com.seohan.HR_APP.dto.history;

import com.seohan.HR_APP.domain.Employee;
import com.seohan.HR_APP.domain.TrainingHistory;
import com.seohan.HR_APP.domain.enumType.TrainingType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTrainingRequestDTO {

    @NotBlank
    private String companyId; //이력 대상자 사번

    @NotNull
    private TrainingType trainingType;

    @NotNull
    private Date trainingDate;

    @NotBlank
    private String trainingName;

    private String trainingContent;

    public TrainingHistory toEntity(Employee employee) {
        return TrainingHistory.builder()
                .employee(employee)
                .trainingType(trainingType)
                .trainingName(trainingName)
                .trainingContent(trainingContent)
                .trainingDate(trainingDate)
                .build();
    }
}
