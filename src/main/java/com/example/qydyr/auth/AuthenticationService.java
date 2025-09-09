package com.example.qydyr.auth;

import com.example.qydyr.config.JwtService;
import com.example.qydyr.exception.EmailAlreadyExistsException;
import com.example.qydyr.model.enums.Role;
import com.example.qydyr.model.User;
import com.example.qydyr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + registerRequest.getEmail());
        }
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .cash(registerRequest.getCash() != null ? registerRequest.getCash() : "10000")
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       authManager.authenticate(new UsernamePasswordAuthenticationToken(
               request.getEmail(),
               request.getPassword()
       ));
       var user = userRepository.findByEmail(request.getEmail())
               .orElseThrow();
       var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }
}
