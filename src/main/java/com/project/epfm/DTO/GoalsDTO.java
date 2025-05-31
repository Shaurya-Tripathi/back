package com.project.epfm.DTO;

import java.time.LocalDate;

public class GoalsDTO {
    private Long employeeId;
    private Long fromEmployeeId;
    private String goalDescription;
    private LocalDate targetDate;
    private String progressStatus;

    public GoalsDTO() {
    }

    public GoalsDTO(Long employeeId, Long fromEmployeeId, String goalDescription, LocalDate targetDate, String progressStatus) {
        this.employeeId = employeeId;
        this.fromEmployeeId = fromEmployeeId;
        this.goalDescription = goalDescription;
        this.targetDate = targetDate;
        this.progressStatus = progressStatus;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getFromEmployeeId() {
        return fromEmployeeId;
    }

    public void setFromEmployeeId(Long fromEmployeeId) {
        this.fromEmployeeId = fromEmployeeId;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }
}

