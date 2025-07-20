package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    User findByUsername(String username);

    // Find user by email
    User findByEmail(String email);
}
