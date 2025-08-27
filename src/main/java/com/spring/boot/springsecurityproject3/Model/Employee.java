package com.spring.boot.springsecurityproject3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    // this will take the id of the user
    private Integer id;

    @NotEmpty(message = "position must not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String position;

    @NotNull(message = "salary must not be empty")
    @Positive(message = "salary must be a positive decimal number")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne
    @MapsId
    @JoinColumn
    private User user;
}
