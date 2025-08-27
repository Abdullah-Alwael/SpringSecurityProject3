package com.spring.boot.springsecurityproject3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "username can not be empty")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "password can not be empty")
    @Size(min = 6, message = "password must at least be 6 characters long")
    private String password;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
    private String name;

    @Email(message = "email must be a valid email")
    private String email;

    private final String role = "EMPLOYEE";

    @NotEmpty(message = "position must not be empty")
    private String position;

    @NotNull(message = "salary must not be empty")
    @Positive(message = "salary must be a positive decimal number")
    private Double salary;

}
