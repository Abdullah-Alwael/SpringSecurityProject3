package com.spring.boot.springsecurityproject3.Controller;

import com.spring.boot.springsecurityproject3.Api.ApiResponse;
import com.spring.boot.springsecurityproject3.Model.Customer;
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

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer added successfully"));

    }
    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }
    @PutMapping("/update/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody Customer customer){
        customerService.updateCustomerPhoneNumber(customerId,customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer updated successfully"));
    }
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer deleted successfully"));
    }
}
