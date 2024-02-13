package com.seohan.HR_APP.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TrainingHistoryResponseDTO {

    @NotNull
    private TrainingType trainingType;

    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date trainingDate;

    @NotBlank
    private String trainingName;

    private String trainingContent;

    public TrainingHistoryResponseDTO(TrainingHistory history) {
        this.trainingType = history.getTrainingType();
        this.trainingDate = history.getTrainingDate();
        this.trainingName = history.getTrainingName();
        this.trainingContent = history.getTrainingContent();
    }
}
