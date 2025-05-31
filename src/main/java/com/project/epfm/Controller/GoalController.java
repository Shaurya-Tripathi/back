package com.project.epfm.Controller;

import com.project.epfm.Entity.Goals;
import com.project.epfm.repository.GoalRepository;
import com.project.epfm.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/employee-manager")
@PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
@RestController
public class GoalController {

    private final GoalRepository goalRepository;
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalRepository goalRepository, GoalService goalService){
        this.goalRepository = goalRepository;
        this.goalService = goalService;
    }

    @GetMapping("/goals-by-eid/{id}")
    public ResponseEntity<Map<String, List<Goals>>> getAllGoalsByEmployeeId(@PathVariable Long id) {
        List<Goals> targetGoals = goalRepository.findByEmployeeEmployeeId(id);

        if (targetGoals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("failure", List.of()));
        } else {
            return ResponseEntity.ok(Map.of("allgoals", targetGoals));
        }
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<Goals> updateStatus(@PathVariable long id, @RequestBody Map<String, String> payload) {
        try {
            String status = payload.get("status");
            Goals updatedGoal = goalService.changeStatus(status, id);
            return ResponseEntity.ok(updatedGoal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }





}
