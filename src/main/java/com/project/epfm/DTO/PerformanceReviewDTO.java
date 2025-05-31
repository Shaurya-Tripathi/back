package com.project.epfm.DTO;


import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PerformanceReviewDTO {

    @NotNull(message = "Employee ID is required.")
    @Min(value = 1, message = "Employee ID must be greater than 0.")
    private Long employeeId;

    @NotNull(message = "Manager ID is required.")
    @Min(value = 1, message = "Manager ID must be greater than 0.")
    private Long managerId;

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


    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
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

