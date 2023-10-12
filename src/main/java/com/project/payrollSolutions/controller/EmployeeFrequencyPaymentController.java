package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentRequestDTO;
import com.project.payrollSolutions.dto.EmployeeFrequencyPaymentResponseDTO;
import com.project.payrollSolutions.model.EmployeeFrequencyPayment;
import com.project.payrollSolutions.model.id.EmployeeFrequencyPaymentId;
import com.project.payrollSolutions.service.EmployeeFrequencyPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmployeeFrequencyPaymentResponseDTO> findEmployeeFrequencyPaymentById(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        var employeeFrequencyPayment = employeeFrequencyPaymentService.findEmployeeFrequencyPaymentById(employeeFrequencyPaymentId);
        return ResponseEntity.ok(employeeFrequencyPayment);
    }

    @PostMapping
    public ResponseEntity<EmployeeFrequencyPayment> createEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        var employeeFrequencyPayment = employeeFrequencyPaymentService.createEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeFrequencyPayment);
    }


    @PutMapping
    public ResponseEntity<Void> updateEmployeeFrequencyPayment(@RequestBody @Valid EmployeeFrequencyPaymentRequestDTO employeeFrequencyPaymentRequestDTO) {
        employeeFrequencyPaymentService.updateEmployeeFrequencyPayment(employeeFrequencyPaymentRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployeeFrequencyPayment(@RequestParam @Valid Long employeeId, @RequestParam @Valid String monthYear) {
        EmployeeFrequencyPaymentId employeeFrequencyPaymentId = new EmployeeFrequencyPaymentId(employeeId, monthYear);
        employeeFrequencyPaymentService.deleteEmployeeFrequencyPayment(employeeFrequencyPaymentId);
        return ResponseEntity.noContent().build();
    }
}
