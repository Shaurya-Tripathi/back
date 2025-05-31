package com.project.epfm.Controller;

import com.project.epfm.DTO.LoginDTO;
import com.project.epfm.DTO.SignupDTO;
import com.project.epfm.service.MyUserDetailsService;
import com.project.epfm.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private SignupService service;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signup(@RequestBody SignupDTO dto){
        service.registerUserAndEmployeeDetails(dto);
        return ResponseEntity.ok(Map.of("message","Singup Successfull"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        String token = (service.verify(dto));

        if ("failure".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        return ResponseEntity.ok(Map.of("token", token));
    }



}
