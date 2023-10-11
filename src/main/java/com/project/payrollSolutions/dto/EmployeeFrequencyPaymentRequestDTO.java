package com.project.payrollSolutions.dto;

import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.model.PaymentInformation;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeFrequencyPaymentRequestDTO {

    @NotNull
    @NotBlank
    private Long employeeId;

    @NotNull
    @NotBlank
    private String monthYear;

    @NotNull
    @NotBlank
    private FrequencyControlRequestDTO frequencyControl;

    @NotNull
    @NotBlank
    private PaymentInformationRequestDTO paymentInformation;

    public EmployeeFrequencyPayment transformToEmployeeFrequencyPayment() {
        FrequencyControl newFrequencyControl = this.frequencyControl.transformToFrequencyControl();
        PaymentInformation newPaymentInformation = this.paymentInformation.transformToPaymentInformation();
        EmployeeFrequencyPaymentId newEmployeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(this.employeeId, this.monthYear);

        return new EmployeeFrequencyPayment(newEmployeeFrequencyPaymentId, newFrequencyControl, newPaymentInformation);
    }
}
