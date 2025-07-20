package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(length = 1000)
    private String message;

    private String type; // Bug Report, Feature Request, etc.

    private LocalDateTime submittedAt;

    @Column(length = 1000)
    private String adminResponse;

    // 👉 No-arg Constructor
    public Feedback() {
        this.submittedAt = LocalDateTime.now();
    }

    // 👉 All-args Constructor (optional but good for testing)
    public Feedback(String name, String email, String message, String type, String adminResponse) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.type = type;
        this.submittedAt = LocalDateTime.now();
        this.adminResponse = adminResponse;
    }

    // 👉 Getters & Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    // 👉 toString() for debugging/logging
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", submittedAt=" + submittedAt +
                ", adminResponse='" + adminResponse + '\'' +
                '}';
    }
}
