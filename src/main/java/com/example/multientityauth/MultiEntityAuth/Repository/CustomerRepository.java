package com.example.multientityauth.MultiEntityAuth.Repository;

import com.example.multientityauth.MultiEntityAuth.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
