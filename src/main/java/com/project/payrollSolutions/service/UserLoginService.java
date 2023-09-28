package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.UserLoginRequestDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.repository.EmployeeRepository;
import com.project.payrollSolutions.repository.UserLoginRepository;
import com.project.payrollSolutions.utils.TransformPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserLoginService {

    private final UserLoginRepository userLoginRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository, EmployeeRepository employeeRepository) {
        this.userLoginRepository = userLoginRepository;
        this.employeeRepository = employeeRepository;
    }

    public void createUserLogin(UserLoginRequestDTO userLogin) {
        UserLogin newUserLogin = userLogin.transformToUserLogin();
        TransformPassword transformPassword = new TransformPassword();

        String password = transformPassword.generateRandomPassword(8);
        newUserLogin.setPassword(password);

        userLoginRepository.save(newUserLogin);
    }

}
