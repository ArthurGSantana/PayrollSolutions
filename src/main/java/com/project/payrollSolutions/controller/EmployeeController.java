package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping
    public List<Employee> findAllEmployee() {
        System.out.println("findAll: " + employeeService.findAllEmployees());
        return employeeService.findAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequestDTO employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
