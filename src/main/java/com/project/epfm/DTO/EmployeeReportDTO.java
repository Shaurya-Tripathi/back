package com.project.epfm.DTO;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Feedbacks;
import com.project.epfm.Entity.Goals;
import com.project.epfm.Entity.PerformanceReview;

import java.util.List;

public class EmployeeReportDTO {

    private Employee employee;
    private List<Feedbacks> feedbacksList;
    private List<PerformanceReview> performanceReviews;
    private List<Goals> goals;

    public EmployeeReportDTO(Employee employee, List<Feedbacks> feedbacksList, List<PerformanceReview> performanceReviews, List<Goals> goals) {
        this.employee = employee;
        this.feedbacksList = feedbacksList;
        this.performanceReviews = performanceReviews;
        this.goals = goals;
    }

    public EmployeeReportDTO() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Feedbacks> getFeedbacksList() {
        return feedbacksList;
    }

    public void setFeedbacksList(List<Feedbacks> feedbacksList) {
        this.feedbacksList = feedbacksList;
    }

    public List<PerformanceReview> getPerformanceReviews() {
        return performanceReviews;
    }

    public void setPerformanceReviews(List<PerformanceReview> performanceReviews) {
        this.performanceReviews = performanceReviews;
    }

    public List<Goals> getGoals() {
        return goals;
    }

    public void setGoals(List<Goals> goals) {
        this.goals = goals;
    }
}
