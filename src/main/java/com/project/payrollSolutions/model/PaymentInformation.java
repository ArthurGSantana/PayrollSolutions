package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "payment_information")
@Entity(name = "payment_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grossSalary;

    private Double additional;

    private Double discounts;

    private Double inssSalary;

    private Double irrfSalary;

    private Double netSalary;

    private LocalDate paymentDate;
}
