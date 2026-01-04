package com.buildrun.encurtadorlinkfbr.core.usecase;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkResponse;
import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.port.in.ShortenLinkPortIn;
import com.buildrun.encurtadorlinkfbr.core.port.out.LinkRepositoryPortOut;
import org.springframework.stereotype.Component;

@Component
public class ShortenLinkUseCase implements ShortenLinkPortIn {

    private final LinkRepositoryPortOut linkRepositoryPortOut;

    public ShortenLinkUseCase(LinkRepositoryPortOut linkRepositoryPortOut) {
        this.linkRepositoryPortOut = linkRepositoryPortOut;
    }

    @Override
    public ShortenLinkResponse execute(Link link) {

        linkRepositoryPortOut.save(link);

        return new ShortenLinkResponse("http://localhost:3000/" + link.getLinkId());
    }

}
