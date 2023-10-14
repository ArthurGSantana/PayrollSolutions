package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.exceptionhandler.NotFoundException;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.repository.AddressRepository;
import com.project.payrollSolutions.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("EmployeeId " + id + " was not found"));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee createEmployee(EmployeeRequestDTO employee) {
        Address address = employee.getAddress().transformToAddress();
        address = addressService.createAddress(address);

        Employee newEmployee = employee.transformToEmployee();
        newEmployee.setAddress(address);

        return employeeRepository.save(newEmployee);
    }

    @Transactional
    public void updateEmployee(EmployeeRequestDTO employeeRequestDTO) {
        findEmployeeById(employeeRequestDTO.getId());
        Employee employee = employeeRequestDTO.transformToEmployee();

        employeeRepository.save(employee);

        Address address = employee.getAddress();
        addressService.updateAddress(address);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);

        Address address = employee.getAddress();
        addressService.deleteAddress(address);
    }
}
