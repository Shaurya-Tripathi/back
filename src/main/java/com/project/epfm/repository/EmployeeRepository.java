package com.project.epfm.repository;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    List<Employee> findByEmployeeId(Long employeeId);
    Employee findByName(String name);
    Employee findByUsers(Users users);
}
