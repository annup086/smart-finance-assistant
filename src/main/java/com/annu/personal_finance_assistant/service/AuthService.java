package com.annu.personal_finance_assistant.service;

import com.annu.personal_finance_assistant.dto.request.LoginRequest;
import com.annu.personal_finance_assistant.dto.request.RegisterRequest;
import com.annu.personal_finance_assistant.dto.response.LoginResponse;
import com.annu.personal_finance_assistant.dto.response.RegisterResponse;
import com.annu.personal_finance_assistant.entity.User;
import com.annu.personal_finance_assistant.exception.EmailAlreadyExistsException;
import com.annu.personal_finance_assistant.exception.InvalidCredentialsException;
import com.annu.personal_finance_assistant.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                hashedPassword
        );

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                "Login successful"
        );
    }
}