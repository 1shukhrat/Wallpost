package ru.wallpost.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("security.jwt.secret-key")
    private String secret;

    @Value("security.jwt.expiration-time")
    private Duration expirationTime;

    public String generateToken(UserDetails userDetails) {
        Date issuedDate = new Date();
        Date expiredDate =
                new Date(issuedDate.getTime() + expirationTime.toMillis());
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }



}
