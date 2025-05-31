package com.project.epfm.service;

import com.project.epfm.DTO.LoginDTO;
import com.project.epfm.DTO.SignupDTO;
import com.project.epfm.Entity.*;
import com.project.epfm.repository.EmployeeRepository;
import com.project.epfm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class SignupService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerUserAndEmployeeDetails(SignupDTO dto){
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        Users savedUser = userRepository.save(user);

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setContactDetails(dto.getContactDetails());
        employee.setRole(dto.getRole());
        employee.setUsers(savedUser);

        Employee savedEmployee = employeeRepository.save(employee);
    }

    public String verify(LoginDTO dto) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(dto.getEmail());
        } else {
            return "failure";
        }
    }


}
