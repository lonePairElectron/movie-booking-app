package com.moviebookingapp.movie_booking_app.JWT;

import com.moviebookingapp.movie_booking_app.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService CustomuserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
       String authHeader =  request.getHeader("Authorization");

       if(authHeader == null || !authHeader.startsWith("Bearer ")) {
           chain.doFilter(request, response);
           return;
       }
         String token = authHeader.substring(7);
       try {
           String username = jwtUtil.extractUsername(token);
           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               UserDetails userDetails = CustomuserDetailsService.loadUserByUsername(username);
               if (jwtUtil.validateToken(token, userDetails)) {
                   UsernamePasswordAuthenticationToken authToken =
                           new UsernamePasswordAuthenticationToken(
                           userDetails.getUsername(),
                                   userDetails.getPassword(),
                                   userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }
           }
       }catch (Exception e){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return;
       }
        chain.doFilter(request, response);
    }
}
