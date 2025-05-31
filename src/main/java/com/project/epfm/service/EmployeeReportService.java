package com.project.epfm.service;

import com.project.epfm.DTO.EmployeeReportDTO;
import com.project.epfm.Entity.*;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.repository.FeedbackRepository;
import com.project.epfm.repository.GoalRepository;
import com.project.epfm.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeReportService {
    private final EmployeeRepository employeeRepository;
    private final FeedbackRepository feedbackRepository;
    private final PerformanceReviewRepository performanceReviewRepository;
    private final GoalRepository goalRepository;

    @Autowired
    public EmployeeReportService(EmployeeRepository employeeRepository,
                                 FeedbackRepository feedbackRepository,
                                 PerformanceReviewRepository performanceReviewRepository,
                                 GoalRepository goalRepository) {
        this.employeeRepository = employeeRepository;
        this.feedbackRepository = feedbackRepository;
        this.performanceReviewRepository = performanceReviewRepository;
        this.goalRepository = goalRepository;
    }

    public EmployeeReportDTO getEmployeeReport(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found by employeeId: "+employeeId ));
        List<Feedbacks> feedbacks = feedbackRepository.findByToEmployee_EmployeeId(employeeId);
        List<PerformanceReview> reviews = performanceReviewRepository.findByEmployee_EmployeeId(employeeId);
        List<Goals> goals = goalRepository.findByEmployeeEmployeeId(employeeId);

        EmployeeReportDTO reportDTO = new EmployeeReportDTO();
        reportDTO.setEmployee(employee);
        reportDTO.setFeedbacksList(feedbacks);
        reportDTO.setPerformanceReviews(reviews);
        reportDTO.setGoals(goals);

        return reportDTO;

    }
}
