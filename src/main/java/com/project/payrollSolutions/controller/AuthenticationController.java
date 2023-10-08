package com.project.payrollSolutions.controller;

import com.project.payrollSolutions.dto.AuthenticationRequestDTO;
import com.project.payrollSolutions.model.UserLogin;
import com.project.payrollSolutions.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDTO data) {
        var userDocumentPassword = new UsernamePasswordAuthenticationToken(data.getDocument(), data.getPassword());

        var auth = this.authenticationManager.authenticate(userDocumentPassword);

        var token = this.tokenService.generateToken((UserLogin) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
}
