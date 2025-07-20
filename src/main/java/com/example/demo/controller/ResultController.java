package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.Services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // ✅ Submit result with current time
    @PostMapping("/submit")
    public String submitResult(@RequestBody Result result) {
        result.setSubmittedAt(LocalDateTime.now());
        resultService.saveResult(result);
        return "✅ Result submitted successfully!";
    }

    // ✅ Get all results (for admin view or total listing)
    @GetMapping("/all")
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    // ✅ Get all results for a specific user — latest first
    @GetMapping("/user/{username}")
    public List<Result> getUserResults(@PathVariable String username) {
        return resultService.getResultsByUsername(username);
    }
}
