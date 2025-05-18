package com.moviebookingapp.movie_booking_app.JWT;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    // Keep this secret safe and ideally load from properties
    private final String secretKey = "ef540102b5187230c73771470b1b31fcfe29f2c10b40822ac44f0a67de2bcff92843d3327183aa8fa424fa00f70d767416b06e3bfd12faf3bc60afb71a062b0867f3f96bba3e5a683af0b2d588bfeb5de5fdc62db6e64f55880f6e852b546bb1d6f8fb70fda950c07edd6e614315fb158864ab1cf4b28d8b0f3f6fc608af937ade311876f3ac4c5798688e42842c4e92180439e55f5386663bd253cfe53de5086e939ced5eab259ff98534f29371b8ff6e09b9b446303f4344bc7b674ceb8b4482c6ac6bafeebba298df1236060cc7d6799df562068e40307b7ccad952caaeff96f8805ca73d4a1bfa7dab4db8ebd6145488028f2867fbc108634cd58ab6b379";

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes());
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
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }
}