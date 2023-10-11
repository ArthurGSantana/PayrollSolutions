package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentRequestDTO;
import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import com.project.payrollSolutions.service.EmployeeFrequencyPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeFrequencyPayment")
public class EmployeeFrequencyPaymentController {
    private final EmployeeFrequencyPaymentService employeeFrequencyPaymentService;

    @Autowired
    public EmployeeFrequencyPaymentController(EmployeeFrequencyPaymentService employeeFrequencyPaymentService) {
        this.employeeFrequencyPaymentService = employeeFrequencyPaymentService;
    }

    @GetMapping
    public EmployeeFrequencyPayment findEmployeeFrequencyPaymentById(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        return employeeFrequencyPaymentService.findEmployeeFrequencyPaymentById(employeeFrequencyPaymentId);
    }

    @PostMapping
    public EmployeeFrequencyPayment createEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        return employeeFrequencyPaymentService.createEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
    }

    @PutMapping
    public void updateEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        employeeFrequencyPaymentService.updateEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
    }

    @DeleteMapping
    public void deleteEmployeeFrequencyPayment(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        employeeFrequencyPaymentService.deleteEmployeeFrequencyPayment(employeeFrequencyPaymentId);
    }
}
