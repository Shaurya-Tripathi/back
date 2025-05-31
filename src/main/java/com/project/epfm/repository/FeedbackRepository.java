package com.project.epfm.repository;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedbacks,Long> {
    List<Feedbacks> findByToEmployee(Employee toEmployee);
    List<Feedbacks> findByToEmployee_EmployeeId(Long employeeId);

}
