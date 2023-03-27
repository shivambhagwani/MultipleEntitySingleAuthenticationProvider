package com.example.multientityauth.MultiEntityAuth.Controller;


import com.example.multientityauth.MultiEntityAuth.Entity.Customer;
import com.example.multientityauth.MultiEntityAuth.Entity.Employee;
import com.example.multientityauth.MultiEntityAuth.Entity.LoginCredentials;
import com.example.multientityauth.MultiEntityAuth.Service.EmployeeService;
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
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.registerEmployee(employee);
        return emp;
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

//    @GetMapping("/getCustomerByUsername")
//    @PreAuthorize("hasAuthority('CUSTOMER')")
//    public Customer getCustomer(@RequestBody String username) {
//        return customerRepository.findByUsername(username);
}

