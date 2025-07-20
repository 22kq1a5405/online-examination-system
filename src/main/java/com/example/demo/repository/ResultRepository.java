package com.example.demo.repository;

import com.example.demo.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    
    // 🔍 Get all results for a particular user
    List<Result> findByUsername(String username);
    
    // 🔽 Get results for a user, latest first
    List<Result> findByUsernameOrderBySubmittedAtDesc(String username);
    
    // 🔽 Get all results, latest first (for your view results page)
    List<Result> findAllByOrderBySubmittedAtDesc();
}
