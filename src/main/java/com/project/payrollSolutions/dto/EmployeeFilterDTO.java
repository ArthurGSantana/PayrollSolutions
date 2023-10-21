package com.project.payrollSolutions.dto;

import lombok.Getter;

@Getter
public class EmployeeFilterDTO {
    private String search;
    private Integer perPage;
    private Integer page;
}
