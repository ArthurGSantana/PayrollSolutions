package com.project.payrollSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_login")
@Entity(name = "user_login")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String document;

    private String password;

    private String role;

    private Boolean active;
}
