package com.zollingpaper.backend.auth.util;

import com.zollingpaper.backend.auth.exception.AuthErrorCode;
import com.zollingpaper.backend.auth.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtCookieConsumer {

    private static final String COOKIE_NAME = "token";

    private final JwtTokenProvider jwtTokenProvider;
    private final SecretKey key;

    public JwtCookieConsumer(JwtTokenProvider jwtTokenProvider, @Value("${jwt.secret-key}") String secretKey) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String extractToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthException(AuthErrorCode.COOKIE_NOT_EXIST);
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_COOKIE));
    }

    public String extractSubject(HttpServletRequest request) {
        String token = extractToken(request);

        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return jwsClaims.getBody().getSubject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }

    public void validateTokenFromCookies(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            throw new AuthException(AuthErrorCode.INVALID_COOKIE);
        }
    }
}
