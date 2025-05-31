package com.project.epfm.Controller;

import com.project.epfm.DTO.EmployeeReportDTO;
import com.project.epfm.DTO.GoalsDTO;
import com.project.epfm.DTO.PerformanceReviewDTO;
import com.project.epfm.Entity.*;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.repository.GoalRepository;
import com.project.epfm.service.EmployeeReportService;
import com.project.epfm.service.EmployeeService;
import com.project.epfm.service.GoalService;
import com.project.epfm.service.PerformanceReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hr")
public class HRController {

    private final EmployeeService employeeService;
    private final GoalService goalService;
    private final GoalRepository goalRepository;
    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewService performanceReviewService;
    private final EmployeeReportService employeeReportService;

    public HRController(EmployeeService employeeService,
                        GoalService goalService,
                        GoalRepository goalRepository,
                        EmployeeRepository employeeRepository,
                        PerformanceReviewService performanceReviewService,
                        EmployeeReportService employeeReportService
                        ) {
        this.employeeService = employeeService;
        this.goalService = goalService;
        this.goalRepository = goalRepository;
        this.employeeRepository = employeeRepository;
        this.performanceReviewService = performanceReviewService;
        this.employeeReportService = employeeReportService;
    }

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/details")
    public ResponseEntity<Employee> getEmployeeDetails(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer", "").trim();
        Employee emp = employeeService.getEmployeeDetailsFromToken(token);
        return ResponseEntity.ok(emp);
    }

    @PreAuthorize("hasRole('HR')")
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

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/addGoal")
    public ResponseEntity<?> addNewGoal(@RequestBody GoalsDTO goalsDTO) {
        try {
            Goals newGoal = goalService.createGoal(goalsDTO);
            return ResponseEntity.ok(newGoal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee ID provided.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/goals-by-eid/{id}")
    public ResponseEntity<Map<String, List<Goals>>> getAllGoalsByEmployeeId(@PathVariable Long id) {
        List<Goals> targetGoals = goalRepository.findByEmployeeEmployeeId(id);

        return ResponseEntity.ok(Map.of("allgoals", targetGoals));
    }

    @PreAuthorize(("hasRole('HR')"))
    @PostMapping("/add-review")
    public ResponseEntity<PerformanceReview> addReview(@Valid @RequestBody PerformanceReviewDTO request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Employee manager = employeeRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));


        PerformanceReview review = new PerformanceReview();
        review.setEmployee(employee);
        review.setManager(manager);
        review.setDate(request.getDate());
        review.setPerformanceScore(request.getPerformanceScore());
        review.setFeedback(request.getFeedback());

        PerformanceReview savedReview = performanceReviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/reviews/by-employee/{employeeId}")
    public ResponseEntity<List<PerformanceReview>> getReviewsByEmployeeId(@PathVariable Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<PerformanceReview> reviews = performanceReviewService.getReviewsByEmployee(employee);
        return ResponseEntity.ok(reviews);
    }

    //Reports

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/employee-report/{employeeId}")
    public ResponseEntity<EmployeeReportDTO> getEmployeeReport(@PathVariable long employeeId){
        return ResponseEntity.ok(employeeReportService.getEmployeeReport(employeeId));
    }

}

