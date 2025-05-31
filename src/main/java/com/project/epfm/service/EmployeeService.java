package com.project.epfm.service;

import com.project.epfm.Entity.Employee;
import com.project.epfm.Entity.Users;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           JWTService jwtService){
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public Employee getEmployeeDetailsFromToken(String token){
        String email = jwtService.extractEmail(token);

        Users user = userRepository.findByEmail(email);
        if(user == null){
            throw  new UsernameNotFoundException("User not found for email "+email);
        }

        Employee employee = employeeRepository.findByUsers(user);
        if(employee == null){
            throw new RuntimeException("Employee details not found for user: "+user);
        }
        return employee;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee emp){
        return employeeRepository.save(emp);
    }
}
