package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.EmployeeRequestDTO;
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
    private final AddressRepository addressRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Employee createEmployee(EmployeeRequestDTO employee) {
        Address address = employee.getAddress().transformToAddress();
        address = addressRepository.save(address);

        Employee newEmployee = employee.transformToEmployee();
        newEmployee.setAddress(address);

        return employeeRepository.save(newEmployee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        if (employeeOptional.isPresent()) {

            Employee newEmployee = getEmployee(employee, employeeOptional.get());
            newEmployee = employeeRepository.save(newEmployee);

            if (newEmployee.getAddress() != null) {
                Address newAddress = getAddress(employee.getAddress(), new Address());
                newAddress = addressRepository.save(newAddress);
                newEmployee.setAddress(newAddress);
                return newEmployee;
            } else {
                throw new RuntimeException("Address by id " + employee.getAddress().getId() + " was not found");
            }
        } else {
            throw new RuntimeException("Employee by id " + employee.getId() + " was not found");
        }
    }

    private Employee getEmployee(Employee employee, Employee newEmployee) {
        newEmployee.setName(employee.getName());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setDocument(employee.getDocument());
        newEmployee.setJobTitle(employee.getJobTitle());
        newEmployee.setBaseSalary(employee.getBaseSalary());
        newEmployee.setPhone(employee.getPhone());
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setAddress(employee.getAddress());
        return newEmployee;
    }

    private Address getAddress(Address address, Address newAddress) {
        newAddress.setStreetAddress(address.getStreetAddress());
        newAddress.setCity(address.getCity());
        newAddress.setPostalCode(address.getPostalCode());
        newAddress.setNeighborhood(address.getNeighborhood());
        newAddress.setHouseNumber(address.getHouseNumber());
        return newAddress;
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employeeRepository.delete(employee);

            if (employee.getAddress() != null) {
                Address address = employee.getAddress();
                addressRepository.delete(address);
            } else {
                throw new RuntimeException("Address by employee id " + employee.getId() + " was not found");
            }
        } else {
            throw new RuntimeException("Employee by id " + id + " was not found");
        }
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> null);

    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
