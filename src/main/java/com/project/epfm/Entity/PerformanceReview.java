package com.project.epfm.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "Employee ID is required.")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    @NotNull(message = "Manager ID is required.")
    private Employee manager; // Reference to the Employees entity (as Manager)

    @NotNull(message = "Date is required.")
    @PastOrPresent(message = "Date cannot be in the future.")
    private LocalDate date;

    @NotNull(message = "Performance Score is required.")
    @Min(value = 1, message = "Performance Score must be at least 1.")
    @Max(value = 100, message = "Performance Score cannot exceed 100.")
    private Integer performanceScore;

    @NotBlank(message = "Feedback is required.")
    @Size(max = 500, message = "Feedback cannot exceed 500 characters.")
    private String feedback;



    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(Integer performanceScore) {
        this.performanceScore = performanceScore;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
