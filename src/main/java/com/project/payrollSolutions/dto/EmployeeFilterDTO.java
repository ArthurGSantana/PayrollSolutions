package com.project.payrollSolutions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmployeeFilterDTO {
    private String search;
    @NotNull
    private Integer perPage;
    @NotNull
    private Integer page;
}
