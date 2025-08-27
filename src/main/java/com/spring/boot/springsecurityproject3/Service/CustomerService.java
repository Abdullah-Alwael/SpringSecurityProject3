package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.DTO.CustomerDTO;
import com.spring.boot.springsecurityproject3.Model.Customer;
import com.spring.boot.springsecurityproject3.Model.User;
import com.spring.boot.springsecurityproject3.Repository.CustomerRepository;
import com.spring.boot.springsecurityproject3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void addCustomer(CustomerDTO customerDTO){
        User user = new User(null, customerDTO.getUsername(), customerDTO.getPassword(),
                customerDTO.getName(), customerDTO.getEmail(), customerDTO.getRole(), null, null);

        Customer customer = new Customer(null, customerDTO.getPhoneNumber(), user, null);

        userService.addUser(user);
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer customerId){
        return customerRepository.findCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerDTO customerDTO){
        Customer oldCustomer = getCustomer(customerId);

        if (oldCustomer == null){
            throw new ApiException("Error, customer not found");
        }

        User oldUser = userService.getUser(oldCustomer.getId());

        if (oldUser == null){
            throw new ApiException("Error, user not found");
        }

        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(oldCustomer);

        oldUser.setName(customerDTO.getName());
        oldUser.setEmail(customerDTO.getEmail());
        oldUser.setUsername(customerDTO.getUsername());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));

        userRepository.save(oldUser);
    }

    public void deleteCustomer(Integer customerId){
        Customer oldCustomer = getCustomer(customerId);

        if (oldCustomer == null){
            throw new ApiException("Error, customer not found");
        }

//        customerRepository.delete(oldCustomer); // user cascade.all
        userService.deleteUser(oldCustomer.getId());
    }
}
