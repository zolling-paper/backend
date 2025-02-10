package com.zollingpaper.backend.auth.service;

import com.zollingpaper.backend.auth.dto.LoginRequest;
import com.zollingpaper.backend.auth.dto.LoginResponse;
import com.zollingpaper.backend.auth.exception.AuthErrorCode;
import com.zollingpaper.backend.auth.exception.AuthException;
import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.board.exception.BoardErrorCode;
import com.zollingpaper.backend.board.exception.BoardException;
import com.zollingpaper.backend.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BoardRepository boardRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(BoardRepository boardRepository, JwtTokenProvider jwtTokenProvider) {
        this.boardRepository = boardRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponse login(LoginRequest request) {
        Board board = boardRepository.findByAccessAddress(request.id())
                .orElseThrow(() -> new BoardException(BoardErrorCode.NOT_FOUND));

        if (!request.password().equals(board.getPassword())) {
            throw new AuthException(AuthErrorCode.INVALID_PASSWORD);
        }

        return new LoginResponse(jwtTokenProvider.createToken(board));
    }
}
