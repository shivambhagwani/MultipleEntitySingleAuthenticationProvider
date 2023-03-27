package com.example.multientityauth.MultiEntityAuth.Repository;

import com.example.multientityauth.MultiEntityAuth.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);
}
