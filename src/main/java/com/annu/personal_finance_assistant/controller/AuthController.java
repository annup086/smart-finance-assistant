package com.annu.personal_finance_assistant.controller;

import com.annu.personal_finance_assistant.dto.request.RegisterRequest;
import com.annu.personal_finance_assistant.dto.response.RegisterResponse;
import com.annu.personal_finance_assistant.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}