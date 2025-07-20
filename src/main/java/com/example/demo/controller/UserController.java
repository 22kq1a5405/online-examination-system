package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔸 User Registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        if (userService.isUsernameTaken(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }
        if (userService.isEmailTaken(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists!");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // 🔸 User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        User existingUser = userService.findByUsername(loginUser.getUsername());

        if (existingUser != null && loginUser.getPassword().equals(existingUser.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // 🔸 Get User by Username (for Profile Page)
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔸 Update User Profile by Username
    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String username, @RequestBody User updatedUser) {
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setYear(updatedUser.getYear());
            existingUser.setLanguage(updatedUser.getLanguage());
            // Optional: Uncomment if password update is allowed
            // existingUser.setPassword(updatedUser.getPassword());

            userService.updateUser(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
