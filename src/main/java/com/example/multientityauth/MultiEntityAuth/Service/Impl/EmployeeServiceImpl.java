package com.example.multientityauth.MultiEntityAuth.Service.Impl;

import com.example.multientityauth.MultiEntityAuth.Entity.Employee;
import com.example.multientityauth.MultiEntityAuth.Repository.EmployeeRepository;
import com.example.multientityauth.MultiEntityAuth.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee registerEmployee(Employee employee) {
        Employee emp = new Employee();
        emp.setAuthorities("EMPLOYEE");
        emp.setAge(employee.getAge());
        emp.setRestaurant(employee.getRestaurant());
        emp.setUsername(employee.getUsername());
        emp.setPassword(passwordEncoder.encode(employee.getPassword()));
        emp.setRestaurant(employee.getRestaurant());

        return employeeRepository.save(emp);
    }
}
