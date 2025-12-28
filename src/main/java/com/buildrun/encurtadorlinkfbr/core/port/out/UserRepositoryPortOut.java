package com.buildrun.encurtadorlinkfbr.core.port.out;

import com.buildrun.encurtadorlinkfbr.core.domain.User;

import java.util.Optional;

public interface UserRepositoryPortOut {

    User save(User user);

    Optional<User> findByEmail(String email);

}
