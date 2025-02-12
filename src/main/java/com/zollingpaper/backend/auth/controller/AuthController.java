package com.zollingpaper.backend.auth.controller;

import com.zollingpaper.backend.auth.service.AuthService;
import com.zollingpaper.backend.auth.dto.LoginRequest;
import com.zollingpaper.backend.auth.dto.LoginResponse;
import com.zollingpaper.backend.auth.util.JwtCookieProvider;
import jakarta.servlet.http.HttpServletResponse;
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
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        LoginResponse loginResponse = authService.login(request);
        JwtCookieProvider.setTokenCookie(response, loginResponse.token());

        return ResponseEntity.ok()
                .body(loginResponse);
    }
}
