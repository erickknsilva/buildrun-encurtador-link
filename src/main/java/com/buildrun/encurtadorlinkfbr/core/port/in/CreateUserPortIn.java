package com.buildrun.encurtadorlinkfbr.core.port.in;

import com.buildrun.encurtadorlinkfbr.core.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface CreateUserPortIn {

    User execute(User userRequest);

}
