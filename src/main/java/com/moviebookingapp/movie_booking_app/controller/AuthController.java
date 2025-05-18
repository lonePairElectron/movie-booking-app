package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.DTO.LoginDTO;
import com.moviebookingapp.movie_booking_app.DTO.UserDTO;
import com.moviebookingapp.movie_booking_app.JWT.JwtUtil;
import com.moviebookingapp.movie_booking_app.entity.Users;
import com.moviebookingapp.movie_booking_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("get")
    public String get() {
        return "Hello";
    }
    // Endpoint for normal user registration (publicly accessible)
    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Passwords do not match!");
        }
        if (userRepository.findByLoginId(userDTO.getLoginId()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("LoginId or Email already exists!");
        }
        Users user = new Users();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setLoginId(userDTO.getLoginId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
        user.setContactNumber(userDTO.getContactNumber());
        user.getRoles().add("ROLE_USER"); // Setting role as ROLE_USER
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Endpoint for admin registration (secured so that only admins can use it)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Passwords do not match!");
        }
        if (userRepository.findByLoginId(userDTO.getLoginId()).isPresent() ||
                userRepository.findByEmail(userDTO.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("LoginId or Email already exists!");
        }
        Users admin = new Users();
        admin.setFirstName(userDTO.getFirstName());
        admin.setLastName(userDTO.getLastName());
        admin.setEmail(userDTO.getEmail());
        admin.setLoginId(userDTO.getLoginId());
        admin.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        admin.setContactNumber(userDTO.getContactNumber());
        admin.getRoles().add("ROLE_ADMIN"); // Setting role as ROLE_ADMIN
        userRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully!");
    }

    // Login endpoint that returns a JWT Bearer token
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getLoginId(), loginDTO.getPassword()
            ));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(Map.of("token", token));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials!"));
        }

    }

    @GetMapping("/{loginId}/forgot")
    public ResponseEntity<String> resetPassword(@PathVariable("loginId") String loginId, @RequestParam String newPassword) {
        Users users = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        users.setPassword(passwordEncoder.encode(newPassword)); // Update password
        userRepository.save(users); // Save updated user

        return ResponseEntity.ok("Password reset successful!");
    }
}