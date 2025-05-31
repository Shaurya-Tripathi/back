package com.project.epfm.service;

import com.project.epfm.Entity.Users;
import com.project.epfm.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

    @Autowired
    private UserRepository repository;

    private String secretKey = "asgj2ngjasdasfe3242424wef3tasgj2ngjasdasfe3242424wef3t";

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        Users user = repository.findByEmail(email); // Fetch user by email
        claims.put("role", user.getRole().name());  // Remove "ROLE_"



        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email.toLowerCase()) // Store email in JWT subject
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (5 * 60 * 60 * 1000)))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) { //extractUsername to extractEmail
        return extractClaim(token, Claims::getSubject); // Extracted email
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser().verifyWith(getKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
//        System.out.println("Extracted Email: " + email);
//        System.out.println("Expected Email: " + userDetails.getUsername());

        if (!email.equals(userDetails.getUsername())) {
            System.out.println("Email mismatch in token validation!");
            return false;
        }

//        System.out.println("Token Expiry Check: " + isTokenExpired(token));

        return !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
