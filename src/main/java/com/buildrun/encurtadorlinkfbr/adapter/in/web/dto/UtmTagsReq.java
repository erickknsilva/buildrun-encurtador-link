package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import com.buildrun.encurtadorlinkfbr.core.domain.UtmTags;

public record UtmTagsReq(
        String utmSource,
        String utmMedium,
        String utmCampaign,
        String utmContent

) {

    public UtmTags toDomain(){
        return new UtmTags(utmSource,utmMedium,utmCampaign,utmContent);
    }
}
