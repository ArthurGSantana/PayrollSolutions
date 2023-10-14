package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.FrequencyControl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FrequencyControlRequestDTO {
    @Null
    private Long id;

    @NotNull
    private Double workedHours;

    @NotNull
    private Integer absences;

    @NotNull
    private Integer justifiedAbsences;

    public FrequencyControl transformToFrequencyControl() {
        return new FrequencyControl(this.id, this.workedHours, this.absences, this.justifiedAbsences);
    }
}
