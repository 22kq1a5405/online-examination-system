package com.example.demo.Services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Check if username is already taken
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // Check if email is already taken
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // Register a new user (plain password)
    public void registerUser(User user) {
        userRepository.save(user);
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Update user details
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
