package com.example.demo.Services;

import com.example.demo.entity.Result;
import com.example.demo.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    // Save result
    public String saveResult(Result result) {
        resultRepository.save(result);
        return "Result submitted successfully";
    }

    // ✅ Fetch all results, latest-first order
    public List<Result> getAllResults() {
        return resultRepository.findAllByOrderBySubmittedAtDesc();
    }

    // ✅ Fetch all results for a specific user, latest-first
    public List<Result> getResultsByUsername(String username) {
        return resultRepository.findByUsernameOrderBySubmittedAtDesc(username);
    }
}
