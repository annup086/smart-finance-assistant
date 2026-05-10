package com.annu.personal_finance_assistant.service;

import com.annu.personal_finance_assistant.dto.request.RegisterRequest;
import com.annu.personal_finance_assistant.dto.response.RegisterResponse;
import com.annu.personal_finance_assistant.entity.User;
import com.annu.personal_finance_assistant.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }
}