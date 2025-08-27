package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.DTO.EmployeeDTO;
import com.spring.boot.springsecurityproject3.Model.Employee;
import com.spring.boot.springsecurityproject3.Model.User;
import com.spring.boot.springsecurityproject3.Repository.EmployeeRepository;
import com.spring.boot.springsecurityproject3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void addEmployee(EmployeeDTO employeeDTO){
        User user = new User(null, employeeDTO.getUsername(), employeeDTO.getPassword(),
                employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getRole(), null, null);

        Employee employee = new Employee(null, employeeDTO.getPosition(), employeeDTO.getSalary(), user);

        userService.addUser(user);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){ // for admin only
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer employeeId){
        return employeeRepository.findEmployeeById(employeeId);
    }

    public void updateEmployee(Integer employeeId, EmployeeDTO employeeDTO){
        Employee oldEmployee = getEmployee(employeeId);

        if(oldEmployee == null){
            throw new ApiException("Error, employee does not exist");
        }

        User oldUser = userService.getUser(oldEmployee.getId());

        if (oldUser == null){
            throw new ApiException("Error, user not found");
        }

        oldEmployee.setPosition(employeeDTO.getPosition());
        oldEmployee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer employeeId){
        Employee oldEmployee = getEmployee(employeeId);

        if(oldEmployee == null){
            throw new ApiException("Error, employee does not exist");
        }

//        employeeRepository.delete(oldEmployee); // user cascade.all
        userService.deleteUser(oldEmployee.getId());
    }
}
