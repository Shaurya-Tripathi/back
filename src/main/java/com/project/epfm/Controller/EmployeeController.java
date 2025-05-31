package com.project.epfm.Controller;

import com.project.epfm.Entity.Employee;
import com.project.epfm.repository.GoalRepository;
import com.project.epfm.service.EmployeeService;
import com.project.epfm.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")

public class EmployeeController {

    private final EmployeeService employeeService;
    private final GoalRepository goalRepository;
    private final GoalService goalService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,GoalRepository goalRepository,GoalService goalService){
        this.employeeService = employeeService;
        this.goalRepository = goalRepository;
        this.goalService = goalService;
    }
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/details")
    public ResponseEntity<Employee> getEmployeeDetails(@RequestHeader("Authorization") String token){
        token =token.replace("Bearer","").trim();
        Employee emp =employeeService.getEmployeeDetailsFromToken(token);
        return ResponseEntity.ok(emp);
    }

@PreAuthorize("hasRole('EMPLOYEE')")
@GetMapping("/all-employees")
public ResponseEntity<?> getAllEmployees() {
    try {
        List<Employee> allEmp = employeeService.getAllEmployees();
        if (allEmp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No employees found in the system.");
        }
        return ResponseEntity.ok(allEmp);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while retrieving employees.");
    }
}
}
