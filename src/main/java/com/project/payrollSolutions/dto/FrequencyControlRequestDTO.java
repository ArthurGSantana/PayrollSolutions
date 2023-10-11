package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.FrequencyControl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FrequencyControlRequestDTO {
    private Long id;

    @NotNull
    @NotBlank
    private Double workedHours;

    @NotNull
    @NotBlank
    private Integer absences;

    @NotNull
    @NotBlank
    private Integer justifiedAbsences;

    public FrequencyControl transformToFrequencyControl() {
        return new FrequencyControl(this.workedHours, this.absences, this.justifiedAbsences);
    }
}
