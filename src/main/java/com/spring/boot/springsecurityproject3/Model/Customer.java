package com.spring.boot.springsecurityproject3.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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

    // TODO add one to one relation with the user model

}
