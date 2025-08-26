package com.spring.boot.springsecurityproject3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username can not be empty")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "password can not be empty")
    @Size(min = 6, message = "password must at least be 6 characters long")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;


    @NotEmpty(message = "name can not be empty")
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "email must be a valid email")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE)$", message = "user role must be one of (CUSTOMER or EMPLOYEE)")
    // Admin role is not allowed to be registered by the server
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @OneToOne
    private Employee employee;
}
