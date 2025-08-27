package com.spring.boot.springsecurityproject3.Controller;

import com.spring.boot.springsecurityproject3.Api.ApiResponse;
import com.spring.boot.springsecurityproject3.DTO.CustomerDTO;
import com.spring.boot.springsecurityproject3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // everyone on the internet
    @PostMapping("/register")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer added successfully"));

    }

    // Admin
    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    // customer user
    @PutMapping("/update/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerId,customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer updated successfully"));
    }

    // customer user
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer deleted successfully"));
    }
}
