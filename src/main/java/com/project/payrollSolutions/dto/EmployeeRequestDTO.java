package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeRequestDTO {
    private Long id;
    private String name;
    private String email;
    private String document;
    private String jobTitle;
    private Double baseSalary;
    private String phone;
    private LocalDate birthDate;

    @NotNull
    private AddressRequestDTO address;

    public Employee transformToEmployee() {
        return new Employee(this.name, this.email, this.document, this.jobTitle, this.baseSalary, this.phone, this.birthDate);
    }
}