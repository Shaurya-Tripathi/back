package com.project.epfm.Controller;

import com.project.epfm.Entity.Employee;
import com.project.epfm.DTO.FeedbackDTO;
import com.project.epfm.Entity.Feedbacks;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/allroles")
@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER', 'HR')")
@RestController
public class FeedBackController {

    private final FeedbackService feedbackService;

    private  final EmployeeRepository employeeRepository;

    public FeedBackController(FeedbackService feedbackService,EmployeeRepository employeeRepository) {
        this.feedbackService = feedbackService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/add-feedback")
    public ResponseEntity<Feedbacks> addFeedback(@RequestBody FeedbackDTO feedbackDto) {

        Employee fromEmployee = employeeRepository.findById(feedbackDto.getFromEmployeeId())
                .orElseThrow(() -> new RuntimeException("From Employee not found"));
        Employee toEmployee = employeeRepository.findById(feedbackDto.getToEmployeeId())
                .orElseThrow(() -> new RuntimeException("To Employee not found"));


        Feedbacks feedback = new Feedbacks();
        feedback.setFeedbackId(feedbackDto.getFeedbackId());
        feedback.setFromEmployee(fromEmployee);
        feedback.setToEmployee(toEmployee);
        feedback.setFeedbackType(feedbackDto.getFeedbackType());
        feedback.setComment(feedbackDto.getComment());

        Feedbacks savedFeedback = feedbackService.addFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/feedback/{employeeId}")
    public List<Feedbacks> getFeedback(@PathVariable Long employeeId) {
        return feedbackService.getFeedbackByEmployeeId(employeeId);
    }

}
