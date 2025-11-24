package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import com.buildrun.encurtadorlinkfbr.core.domain.User;

public record CreateUserRequest(
        String email,
        String nickname,
        String password
) {

    public User toDomain(){
        return new User(email,nickname,password);
    }

}
