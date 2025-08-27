package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.Model.Employee;
import com.spring.boot.springsecurityproject3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){ // for admin only
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer employeeId){
        return employeeRepository.findEmployeeById(employeeId);
    }

    public void updateEmployee(Integer employeeId, Employee employee){
        Employee oldEmployee = getEmployee(employeeId);

        if(oldEmployee == null){
            throw new ApiException("Error, employee does not exist");
        }

        oldEmployee.setPosition(employee.getPosition());
        oldEmployee.setSalary(employee.getSalary());

        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer employeeId){
        Employee oldEmployee = getEmployee(employeeId);

        if(oldEmployee == null){
            throw new ApiException("Error, employee does not exist");
        }

        employeeRepository.delete(oldEmployee);
    }
}
