package com.project.epfm.repository;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview,Long> {

    List<PerformanceReview> findByEmployee(Employee employee);
    List<PerformanceReview> findByEmployee_EmployeeId(long employeeId);


}
