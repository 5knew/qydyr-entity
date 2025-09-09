package com.example.qydyr.controller;

import com.example.qydyr.auth.AuthenticationRequest;
import com.example.qydyr.auth.AuthenticationResponse;
import com.example.qydyr.auth.AuthenticationService;
import com.example.qydyr.auth.RegisterRequest;
import com.example.qydyr.exception.UserNotFoundException;
import com.example.qydyr.model.User;
import com.example.qydyr.repository.UserRepository;
import com.example.qydyr.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;

    private final AuthenticationService service;
    private final UserRepository userRepository;
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && 
                !authentication.getName().equals("anonymousUser")) {
                User user = userService.getUserByEmail(authentication.getName());
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken(HttpServletRequest request) {
        System.err.println("=== /api/v1/auth/token called ===");
        System.err.println("Session ID: " + request.getSession().getId());
        System.err.println("Session is new: " + request.getSession().isNew());
        System.err.println("Session attributes: " + java.util.Collections.list(request.getSession().getAttributeNames()));
        
        try {
            String jwtToken = (String) request.getSession().getAttribute("JWT_TOKEN");
            System.err.println("JWT token from session: " + (jwtToken != null ? "EXISTS" : "NULL"));
            
            if (jwtToken != null) {
                System.err.println("Returning JWT token to client");
                return ResponseEntity.ok().body(Map.of("token", jwtToken));
            } else {
                System.err.println("No JWT token found in session");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found in session");
            }
        } catch (Exception e) {
            System.err.println("Error getting token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error getting token: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // For stateless JWT, logout is handled client-side by discarding the token.
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/deposit")
    public ResponseEntity<String> depositCash(@PathVariable Long userId, @RequestParam double amount) {
        try {
            userService.depositCash(userId, amount);
            return ResponseEntity.ok("Cash deposited successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
