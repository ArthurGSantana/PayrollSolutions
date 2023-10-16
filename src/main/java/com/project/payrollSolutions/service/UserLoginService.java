package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.UserLoginRequestDTO;
import com.project.payrollSolutions.email.SendEmailMessage;
import com.project.payrollSolutions.email.SendEmailService;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.repository.UserLoginRepository;
import com.project.payrollSolutions.utils.TransformPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLoginService {
    private final UserLoginRepository userLoginRepository;
    private final EmployeeService employeeService;
    private final SendEmailService sendEmailService;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository, EmployeeService employeeService, SendEmailService sendEmailService) {
        this.userLoginRepository = userLoginRepository;
        this.employeeService = employeeService;
        this.sendEmailService = sendEmailService;
    }

    @Transactional
    public void createUserLogin(UserLoginRequestDTO userLogin) {
        UserLogin newUserLogin = userLogin.transformToUserLogin();

        if (this.userLoginRepository.findByDocument(newUserLogin.getDocument()) != null) {
            throw new RuntimeException("User has already been created!");
        }

        if (userLogin.getPassword() == null) {
            TransformPassword transformPassword = new TransformPassword();
            String password = transformPassword.generateRandomPassword(8);
            userLogin.setPassword(password);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userLogin.getPassword());

        newUserLogin.setPassword(encryptedPassword);

        userLoginRepository.save(newUserLogin);

        if (userLogin.getEmployeeId() != null) {
            this.sendEmailToEmployee(userLogin);
        }
    }

    private void sendEmailToEmployee(UserLoginRequestDTO userLogin) {
        var employee = employeeService.findEmployeeById(userLogin.getEmployeeId());
        var subject = SendEmailMessage.createSubject(employee.getName());
        var text = SendEmailMessage.createText(employee.getName(), employee.getDocument(), userLogin.getPassword());

        sendEmailService.sendEmail(employee.getEmail(), subject, text);
    }

    public void sendEmailToEmployee(Long employeeId) {
        var employee = employeeService.findEmployeeById(employeeId);
        var userLogin = userLoginRepository.findByDocument(employee.getDocument());

        var subject = SendEmailMessage.createSubject(employee.getName());
        var text = SendEmailMessage.createText(employee.getName(), employee.getDocument(), userLogin.getPassword());

        sendEmailService.sendEmail(employee.getEmail(), subject, text);
    }
}
