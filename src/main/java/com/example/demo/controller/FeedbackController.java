package com.example.demo.controller;

import com.example.demo.entity.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*") // allow frontend to access
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // 👉 Submit feedback (User)
    @PostMapping("/save")
    public String saveFeedback(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);
        return "Feedback submitted successfully!";
    }

    // 👉 Get all feedback (Admin)
    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // 👉 (OPTIONAL) Remove: Sending response — you decided no reply, only view & accept
    // Keeping this commented in case you later want it
    /*
    @PutMapping("/{id}/respond")
    public String respondToFeedback(@PathVariable Long id, @RequestBody String response) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isPresent()) {
            Feedback feedback = optionalFeedback.get();
            feedback.setAdminResponse(response);
            feedbackRepository.save(feedback);
            return "Response sent successfully!";
        } else {
            return "Feedback not found!";
        }
    }
    */

    // 👉 Get feedback by user email (for user-side lookup)
    @GetMapping("/by-email/{email}")
    public List<Feedback> getFeedbackByEmail(@PathVariable String email) {
        return feedbackRepository.findByEmail(email);
    }
}
