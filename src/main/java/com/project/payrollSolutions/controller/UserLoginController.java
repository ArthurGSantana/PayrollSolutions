package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.UserLoginRequestDTO;
import com.project.payrollSolutions.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserLoginController {
    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping
    public ResponseEntity createUserLogin(@RequestBody UserLoginRequestDTO user) {
        this.userLoginService.createUserLogin(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Void> sendEmailToEmployee(Long employeeId) {
        userLoginService.sendEmailToEmployee(employeeId);

        return ResponseEntity.noContent().build();
    }

}
