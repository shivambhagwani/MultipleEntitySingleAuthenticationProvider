package com.example.multientityauth.MultiEntityAuth.Repository;

import com.example.multientityauth.MultiEntityAuth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
