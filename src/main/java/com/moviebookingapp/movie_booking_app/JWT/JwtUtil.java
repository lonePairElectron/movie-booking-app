package com.moviebookingapp.movie_booking_app.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private  SecretKey SECRET_KEY ;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct  // **Ensures execution after dependency injection**
    public void init() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expires in 1 hour
                .signWith(SECRET_KEY,Jwts.SIG.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return  extractUsername(token).equals(userDetails.getUsername());

    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

//                Jwts.parser()
//                .verifyWith(SECRET_KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getPayload()
//                .getSubject();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public SecretKey getSignInKey() {
        return SECRET_KEY;
    }
}