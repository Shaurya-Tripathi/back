package com.project.epfm.service;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Goals;
import com.project.epfm.DTO.GoalsDTO;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.repository.GoalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, EmployeeRepository employeeRepository){
        this.goalRepository = goalRepository;
        this.employeeRepository = employeeRepository;
    }

    public Goals createGoal(GoalsDTO goalsDTO) {

        Employee employee = employeeRepository.findById(goalsDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));


        Goals goal = new Goals();
        goal.setEmployee(employee);
        goal.setGoalDescription(goalsDTO.getGoalDescription());
        goal.setTargetDate(goalsDTO.getTargetDate());
        goal.setProgressStatus(goalsDTO.getProgressStatus());

        return goalRepository.save(goal);
    }

    public ResponseEntity<Map<String, List<Goals>>> getAllGoalsByEmployeeId(Long id) {
        List<Goals> targetGoals = goalRepository.findByEmployeeEmployeeId(id);

        if (targetGoals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("failure", List.of()));
        } else {
            return ResponseEntity.ok(Map.of("allgoals", targetGoals));
        }
    }

    @Transactional
    public Goals changeStatus(String progressStatus, long id) {
        if (progressStatus == null || progressStatus.isBlank()) {
            throw new IllegalArgumentException("Progress status must not be null or blank.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Goal ID must be greater than zero.");
        }

        Goals target = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found with ID: " + id));

        target.setProgressStatus(progressStatus);
        System.out.println("Saving Goal with ID: " + target.getGoalId() + " and Status: " + target.getProgressStatus());

        return goalRepository.save(target);
    }

}
