package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.UserLoginRequestDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.repository.UserLoginRepository;
import com.project.payrollSolutions.utils.TransformPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserLoginService {

    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

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
    }
}
