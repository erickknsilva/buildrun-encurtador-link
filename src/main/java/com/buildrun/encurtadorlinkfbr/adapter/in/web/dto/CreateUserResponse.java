package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import com.buildrun.encurtadorlinkfbr.core.domain.User;

import java.time.LocalDateTime;

public record CreateUserResponse(
        String userId,
        LocalDateTime createAt) {

    public static CreateUserResponse fromDomain(User user){
        return new CreateUserResponse(user.getUserId().toString(), user.getCreateAt());
    }

}
