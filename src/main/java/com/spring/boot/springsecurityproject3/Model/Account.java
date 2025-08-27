package com.spring.boot.springsecurityproject3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "accountNumber must not be empty")
    @Pattern(regexp = "^\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d$",
            message = "must follow the pattern XXXX-XXXX-XXXX-XXXX")
    @Column(columnDefinition = "int not null")
    private Integer accountNumber;

    @NotNull(message = "balance must not be empty")
    @Positive(message = "balance must be positive decimal number")
    @Column(columnDefinition = "double not null")
    private Double balance;


    @Column(columnDefinition = "boolean not null default false")
    private Boolean isActive = false;

    @ManyToOne
    @JoinColumn
    private Customer customer;
}
