package com.zollingpaper.backend.board.service;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BoardAccessAddressGenerator implements AccessAddressGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
