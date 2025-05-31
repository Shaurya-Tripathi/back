package com.project.epfm.service;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Feedbacks;
import com.project.epfm.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }

    public Feedbacks addFeedback(Feedbacks feedbacks){
        return feedbackRepository.save(feedbacks);
    }

    public List<Feedbacks> getFeedbackByEmployeeId(Long employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        return feedbackRepository.findByToEmployee(employee);
    }
}
