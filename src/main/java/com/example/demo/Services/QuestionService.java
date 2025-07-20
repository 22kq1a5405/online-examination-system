package com.example.demo.Services;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // ✅ Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // ✅ Get question by ID
    public Question getQuestionById(Long id) {
        Optional<Question> optional = questionRepository.findById(id);
        return optional.orElse(null);
    }

    // ✅ Get questions by subject
    public List<Question> getQuestionsBySubject(String subject) {
        return questionRepository.findBySubject(subject);
    }

    // ✅ Add a new question
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // ✅ Update an existing question (safe, supports optional subject/marks)
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existing = questionRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setQuestionText(updatedQuestion.getQuestionText());
            existing.setOption1(updatedQuestion.getOption1());
            existing.setOption2(updatedQuestion.getOption2());
            existing.setOption3(updatedQuestion.getOption3());
            existing.setOption4(updatedQuestion.getOption4());
            existing.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

            // Optional updates — if frontend sends them
            if (updatedQuestion.getSubject() != null) {
                existing.setSubject(updatedQuestion.getSubject());
            }
            if (updatedQuestion.getMarks() != null) {
                existing.setMarks(updatedQuestion.getMarks());
            }

            return questionRepository.save(existing);
        }
        return null;
    }

    // ✅ Delete a question
    public String deleteQuestion(Long id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            return "Question deleted successfully.";
        }
        return "Question not found.";
    }
}
