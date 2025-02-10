package com.zollingpaper.backend.auth.controller;

import com.zollingpaper.backend.auth.service.AuthService;
import com.zollingpaper.backend.auth.dto.LoginRequest;
import com.zollingpaper.backend.auth.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + response.token())
                .body(response);
    }
}
