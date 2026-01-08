package com.buildrun.encurtadorlinkfbr.core.port.out;

import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface LinkRepositoryPortOut {

    Link save (Link link);

    Optional<Link> findByOriginalUrl(String linkId);
}
