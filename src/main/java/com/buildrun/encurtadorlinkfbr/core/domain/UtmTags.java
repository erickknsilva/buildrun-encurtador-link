package com.buildrun.encurtadorlinkfbr.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtmTags {

    private String utmSource;
    private String utmMedium;
    private String umtCampaign;
    private String utmContent;

    public static UtmTags toDomain(UtmTags tags){
        return new UtmTags(tags.getUtmSource(),tags.getUtmMedium(),tags.getUmtCampaign(),tags.getUtmContent());
    }


}
