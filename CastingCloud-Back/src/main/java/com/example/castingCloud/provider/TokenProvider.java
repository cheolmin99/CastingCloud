package com.example.castingCloud.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {
    @Value("${jwt.security-key}")
    private String SECURITY_KEY;

    public String create (String email) {
        Date expiredTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                        .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredTime)
                        .compact();
                    
                    return jwt;
    }

    public String createForAdmin(String adminEmail) {
        Date expiredTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .claim("role", "admin")  // Add a claim to identify the role
                .setSubject(adminEmail)
                .setIssuedAt(new Date())
                .setExpiration(expiredTime)
                .compact();

        return jwt;
    }

    public String createForActor(String actorEmail) {
        Date expiredTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .claim("role", "actor")  // Add a claim to identify the role
                .setSubject(actorEmail)
                .setIssuedAt(new Date())
                .setExpiration(expiredTime)
                .compact();

        return jwt;
    }

    public String createForDirector(String directorEmail) {
        Date expiredTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .claim("role", "director")  // Add a claim to identify the role
                .setSubject(directorEmail)
                .setIssuedAt(new Date())
                .setExpiration(expiredTime)
                .compact();

        return jwt;
    }

    public String validate (String jwt) {
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
