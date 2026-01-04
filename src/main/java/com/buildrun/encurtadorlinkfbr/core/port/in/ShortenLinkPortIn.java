package com.buildrun.encurtadorlinkfbr.core.port.in;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkResponse;
import com.buildrun.encurtadorlinkfbr.core.domain.Link;

public interface ShortenLinkPortIn {

    ShortenLinkResponse execute(Link link);
}
