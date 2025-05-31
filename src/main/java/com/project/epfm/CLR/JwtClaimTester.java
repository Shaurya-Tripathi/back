package com.project.epfm.CLR;


import com.project.epfm.repository.UserRepository;
import com.project.epfm.service.JWTService;
import com.project.epfm.service.MyUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtClaimTester implements CommandLineRunner {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final MyUserDetailsService userDetailsService;

    public JwtClaimTester(JWTService jwtService, UserRepository userRepository, MyUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void run(String... args) {
//        String testEmail = "ABCD26@gmail.com";
//        String token = jwtService.generateToken(testEmail);
//
//        System.out.println("Generated Token: " + token);
//
//        String extractedEmail = jwtService.extractEmail(token);
//        System.out.println("Extracted Email: " + extractedEmail);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(extractedEmail);
//        System.out.println("User Authorities: " + userDetails.getAuthorities());
//
//        if (jwtService.validateToken(token, userDetails)) {
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//
//            System.out.println("Authentication set successfully!");
//        } else {
//            System.out.println("Token validation failed!");
//        }
    }
}
