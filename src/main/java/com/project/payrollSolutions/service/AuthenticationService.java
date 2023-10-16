package com.project.payrollSolutions.service;

import com.project.payrollSolutions.dto.AuthenticationRequestDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public String login(AuthenticationRequestDTO data) {
        var userDocumentPassword = new UsernamePasswordAuthenticationToken(data.getDocument(), data.getPassword());

        try {
            var auth = this.authenticationManager.authenticate(userDocumentPassword);
            return this.tokenService.generateToken((UserLogin) auth.getPrincipal());

        } catch (AuthenticationException exception) {
            throw new com.project.payrollSolutions.exceptionhandler.AuthenticationException("Authentication error", exception);
        }
    }
}
