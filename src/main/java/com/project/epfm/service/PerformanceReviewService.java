package com.project.epfm.service;

import com.project.epfm.Entity.PerformanceReview;
import com.project.epfm.Entity.Employee;
import com.project.epfm.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReviewService {
    private final PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    public PerformanceReviewService(PerformanceReviewRepository performanceReviewRepository){
        this.performanceReviewRepository = performanceReviewRepository;
    }


    public PerformanceReview addReview(PerformanceReview review) {
        return performanceReviewRepository.save(review);
    }


    public List<PerformanceReview> getReviewsByEmployee(Employee employee) {
        return performanceReviewRepository.findByEmployee(employee);
    }
}
