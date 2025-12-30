package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

public record LoginResponse(
        String accessToken,
        long expiresIn
) {
}
