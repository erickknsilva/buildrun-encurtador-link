package com.buildrun.encurtadorlinkfbr.core.port.out;

import com.buildrun.encurtadorlinkfbr.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPortOut {

    User save(User user);

    Optional<User> findByEmail(String email);

    void deleteById(String email);

    Optional<User> findById(UUID userId);
}
