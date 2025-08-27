package com.spring.boot.springsecurityproject3.DTO;

import com.spring.boot.springsecurityproject3.Model.Account;
import com.spring.boot.springsecurityproject3.Model.Customer;
import com.spring.boot.springsecurityproject3.Model.Employee;
import com.spring.boot.springsecurityproject3.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class CustomerDTO {

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

    private final String role = "CUSTOMER";

    @NotEmpty(message = "phoneNumber must not be empty")
    @Pattern(regexp = "^05.{8}$", message = "phoneNumber must start with 05 and 10 numbers long")
    private String phoneNumber;

}
