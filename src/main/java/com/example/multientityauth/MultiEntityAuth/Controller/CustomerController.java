package com.example.multientityauth.MultiEntityAuth.Controller;

import com.example.multientityauth.MultiEntityAuth.Entity.Customer;
import com.example.multientityauth.MultiEntityAuth.Entity.LoginCredentials;
import com.example.multientityauth.MultiEntityAuth.Repository.CustomerRepository;
import com.example.multientityauth.MultiEntityAuth.Service.CustomerService;
import com.example.multientityauth.MultiEntityAuth.Service.Impl.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.registerCustomer(customer);
        return customer1;
    }

    @PostMapping("/authenticate")
    public String customerLogin(@RequestBody LoginCredentials credentials) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(credentials.getUsername());
        } else {
            throw new UsernameNotFoundException("Username not found.");
        }
    }

    @GetMapping("/getCustomerByUsername")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Customer getCustomer(@RequestBody String username) {
        return customerRepository.findByUsername(username);
    }


}
