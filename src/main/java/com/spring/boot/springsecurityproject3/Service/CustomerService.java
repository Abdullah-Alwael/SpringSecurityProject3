package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.Model.Customer;
import com.spring.boot.springsecurityproject3.Model.User;
import com.spring.boot.springsecurityproject3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    public void addCustomer(Customer customer){
        User user = new User(customer.getId(), customer.get);
        // TODO create DTO

        userService.addUser(user);
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer customerId){
        return customerRepository.findCustomerById(customerId);
    }

    public void updateCustomerPhoneNumber(Integer customerId, Customer customer){
        Customer oldCustomer = getCustomer(customerId);

        if (oldCustomer == null){
            throw new ApiException("Error, customer not found");
        }

        oldCustomer.setPhoneNumber(customer.getPhoneNumber());

        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer customerId){
        Customer oldCustomer = getCustomer(customerId);

        if (oldCustomer == null){
            throw new ApiException("Error, customer not found");
        }

//        customerRepository.delete(oldCustomer); // user cascade.all
        userService.deleteUser(oldCustomer.getUser().getId());
    }
}
