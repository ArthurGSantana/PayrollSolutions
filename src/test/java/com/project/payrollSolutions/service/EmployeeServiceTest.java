package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.AddressRequestDTO;
import com.project.payrollSolutions.dto.EmployeeRequestDTO;
import com.project.payrollSolutions.model.Address;
import com.project.payrollSolutions.model.Employee;
import com.project.payrollSolutions.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    AddressService addressService;

    private Employee employee;
    private EmployeeRequestDTO employeeRequest;
    private List<Employee> employees;
    private Address address;
    private AddressRequestDTO addressRequestDTO;

    @BeforeEach
    void setup() {
        address = new Address(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138");
        employee = new Employee(1L, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("1999-02-28"), address);
        addressRequestDTO = new AddressRequestDTO(1L, "Rua teste", "Sao Paulo", "13235678", "Zona Sul", "138");
        employeeRequest = new EmployeeRequestDTO(1L, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("1999-02-28"), addressRequestDTO);
        employees = new ArrayList<Employee>();

        for (int i = 0; i < 10; i++) {
            employees.add(new Employee((long) i + 1, "Arthur", "teste@teste.com", "45464677798", "Developer", 2500D, "992785578", LocalDate.parse("1999-02-28"), address));
        }
    }

    @Test
    void findEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        var employeeTest = employeeService.findEmployeeById(1L);

        assertEquals(employeeTest, employee);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void search() {
        when(employeeRepository.search("art", PageRequest.of(0, 10))).thenReturn(pageOfEmployees());

        var result = employeeService.search("art");

        assertNotNull(result);
        assertEquals(1, result.getData().size());
    }


    @Test
    void findAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(employees);

        var result = employeeService.findAllEmployees();

        assertEquals(result.size(), employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void createEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(addressService.createAddress(any(Address.class))).thenReturn(address);

        var result = employeeService.createEmployee(employeeRequest);

        assertNotNull(result);
        assertEquals("Arthur", result.getName());
    }

    @Test
    void updateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.updateEmployee(employeeRequest);

        verify(employeeRepository, times(1)).save(employeeRequest.transformToEmployee());
        verify(addressService, times(1)).updateAddress(employee.getAddress());
    }

    @Test
    void deleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(any(Employee.class));
        verify(addressService, times(1)).deleteAddress(any(Address.class));
    }

    private Page<Employee> pageOfEmployees() {
        return new PageImpl<>(List.of(employee));
    }
}