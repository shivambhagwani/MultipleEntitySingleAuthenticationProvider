package com.example.multientityauth.MultiEntityAuth.Service.Impl;

import com.example.multientityauth.MultiEntityAuth.Entity.Customer;
import com.example.multientityauth.MultiEntityAuth.Entity.User;
import com.example.multientityauth.MultiEntityAuth.Repository.CustomerRepository;
import com.example.multientityauth.MultiEntityAuth.Repository.UserRepository;
import com.example.multientityauth.MultiEntityAuth.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


    final
    UserRepository userRepository;

    final
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer registerCustomer(Customer customer) {
        Customer cus = new Customer();
        cus.setUsername(customer.getUsername());
        cus.setPassword(passwordEncoder.encode(customer.getPassword()));
        cus.setAuthorities("CUSTOMER");
        cus.setFavCuisine(customer.getFavCuisine());
        cus.setCity(customer.getCity());

        return customerRepository.save(cus);
    }
}
