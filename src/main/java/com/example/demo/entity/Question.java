package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "examquestion")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question text is required")
    @Column(nullable = false)
    private String questionText;

    @NotBlank(message = "Option1 is required")
    @Column(nullable = false)
    private String option1;

    @NotBlank(message = "Option2 is required")
    @Column(nullable = false)
    private String option2;

    @NotBlank(message = "Option3 is required")
    @Column(nullable = false)
    private String option3;

    @NotBlank(message = "Option4 is required")
    @Column(nullable = false)
    private String option4;

    @NotBlank(message = "Correct answer is required")
    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private Integer marks;

    // 👉 Default values setter before persisting
    @PrePersist
    public void setDefaults() {
        if (this.subject == null || this.subject.trim().isEmpty()) {
            this.subject = "General";
        }
        if (this.marks == 0) {
            this.marks = 5;
        }
    }

    // ✅ Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
