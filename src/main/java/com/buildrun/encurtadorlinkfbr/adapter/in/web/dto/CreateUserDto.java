package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

public record CreateUserDto(
        String email,
        String nickname,
        String password
) {
}
