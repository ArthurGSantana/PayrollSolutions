package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.PaymentInformation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentInformationRequestDTO {
    private Long id;

    @NotNull
    @NotBlank
    private Double grossSalary;

    @NotNull
    @NotBlank
    private Double additional;

    @NotNull
    @NotBlank
    private Double discounts;

    @NotNull
    @NotBlank
    private Double inssSalary;

    @NotNull
    @NotBlank
    private Double irrfSalary;

    @NotNull
    @NotBlank
    private Double netSalary;

    @NotNull
    @NotBlank
    private LocalDate paymentDate;

    public PaymentInformation transformToPaymentInformation() {
        return new PaymentInformation(this.grossSalary, this.additional, this.discounts, this.inssSalary, this.irrfSalary, this.netSalary, this.paymentDate);
    }
}
