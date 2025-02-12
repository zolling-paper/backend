package com.zollingpaper.backend.auth.controller;

import com.zollingpaper.backend.auth.service.AuthService;
import com.zollingpaper.backend.auth.dto.LoginRequest;
import com.zollingpaper.backend.auth.dto.LoginResponse;
import com.zollingpaper.backend.auth.util.JwtCookieProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth API")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "login API", description = "board의 accessAddress, password로 로그인합니다.")
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        LoginResponse loginResponse = authService.login(request);
        JwtCookieProvider.setTokenCookie(response, loginResponse.token());

        return ResponseEntity.ok()
                .build();
    }
}
