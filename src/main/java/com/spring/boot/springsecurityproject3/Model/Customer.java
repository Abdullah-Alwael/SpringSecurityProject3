package com.spring.boot.springsecurityproject3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    // this will take the id of the user
    private Integer id;

    @NotEmpty(message = "phoneNumber must not be empty")
    @Pattern(regexp = "^05.{8}$", message = "phoneNumber must start with 05 and 10 numbers long")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Account> account;

}
