package com.spring.boot.springsecurityproject3.Controller;

import com.spring.boot.springsecurityproject3.Api.ApiResponse;
import com.spring.boot.springsecurityproject3.DTO.EmployeeDTO;
import com.spring.boot.springsecurityproject3.Model.Employee;
import com.spring.boot.springsecurityproject3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // Admin
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee added successfully"));

    }

    // Admin
    @GetMapping("/list")
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    // Admin
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer employeeId, @Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeId,employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee updated successfully"));
    }

    // Admin
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee deleted successfully"));
    }
}
