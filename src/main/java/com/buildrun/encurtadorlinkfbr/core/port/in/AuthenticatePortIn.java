package com.buildrun.encurtadorlinkfbr.core.port.in;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginResponse;
import com.buildrun.encurtadorlinkfbr.core.domain.User;

public interface AuthenticatePortIn {

    LoginResponse execute(LoginRequest loginRequest);

}
