package com.project.epfm.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="goals")
public class Goals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long goalId;

    @ManyToOne
    @JoinColumn(name ="employee_id",nullable = false)
    @NotNull(message = "Employee must not be null.")
    private Employee employee;


    @Column(name="goal_description", nullable = false)
    @NotBlank(message = "goal description must not be blank")
    @Size(max=255, message = "goal description must not exceed 255 characters")
    private String goalDescription;

    @Column(name="target_date", nullable = false)
    @NotNull(message = "Target date must not be null")
    @Future(message = "Target date must be a future date")
    private LocalDate targetDate;

    @Column(name="progress_status", nullable = false)
    @NotBlank(message = "Progress status must not be blank")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Progress status must be 'Not Started', 'In Progress', or 'Completed'.")
    private String progressStatus;

    public Goals() {
    }

    public Goals(long goalId, long employeeId, String goalDescription, LocalDate targetDate, String progressStatus) {
        this.goalId = goalId;
        this.employee = employee;
        this.goalDescription = goalDescription;
        this.targetDate = targetDate;
        this.progressStatus = progressStatus;
    }

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getFormattedTargetDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return targetDate != null ? targetDate.format(formatter) : null;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

}
