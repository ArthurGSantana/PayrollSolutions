package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeRequestDTO {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String document;

    @NotNull
    @NotBlank
    private String jobTitle;

    @NotNull
    private Double baseSalary;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private AddressRequestDTO address;

    public Employee transformToEmployee() {
        Address address = this.address.transformToAddress();
        return new Employee(this.id, this.name, this.email, this.document, this.jobTitle, this.baseSalary, this.phone, this.birthDate, address);
    }
}