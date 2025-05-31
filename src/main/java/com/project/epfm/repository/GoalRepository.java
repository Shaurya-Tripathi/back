package com.project.epfm.repository;

import com.project.epfm.Entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goals,Long> {
    List<Goals> findByEmployeeEmployeeId(Long employeeId);
}
