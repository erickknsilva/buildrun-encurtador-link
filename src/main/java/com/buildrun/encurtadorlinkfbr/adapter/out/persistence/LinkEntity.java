package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.domain.User;
import com.buildrun.encurtadorlinkfbr.core.domain.UtmTags;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@DynamoDbBean
public class LinkEntity {


    private String linkId;
    private String originalUrl;
    private String utmSource;
    private String utmMedium;
    private String umtCampaign;
    private String utmContent;

    private UUID userId;
    private boolean active;
    private LocalDateTime expirationDateTime;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public LinkEntity() {
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("link_id")
    @DynamoDbSecondarySortKey(indexNames = Constants.FK_USER_INDEX)
    public String getLinkId() {
        return linkId;
    }

    @DynamoDbAttribute("original_url")
    public String getOriginalUrl() {
        return originalUrl;
    }

    @DynamoDbAttribute("utm_source")
    public String getUtmSource() {
        return utmSource;
    }

    @DynamoDbAttribute("utm_medium")
    public String getUtmMedium() {
        return utmMedium;
    }

    @DynamoDbAttribute("utm_campaign")
    public String getUmtCampaign() {
        return umtCampaign;
    }

    @DynamoDbAttribute("utm_content")
    public String getUtmContent() {
        return utmContent;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = Constants.FK_USER_INDEX)
    @DynamoDbAttribute("user_id")
    public UUID getUserId() {
        return userId;
    }

    @DynamoDbAttribute("active")
    public boolean isActive() {
        return active;
    }

    @DynamoDbAttribute("expiration_date_time")
    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    @DynamoDbAttribute("create_at")
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @DynamoDbAttribute("update_at")
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }


    public static LinkEntity fromDomain(Link link) {
        LinkEntity entity = new LinkEntity();

        entity.setLinkId(link.getLinkId());
        entity.setOriginalUrl(link.getOriginalUrl());

        entity.setUtmSource(link.getUtmTags().getUtmSource());
        entity.setUtmMedium(link.getUtmTags().getUtmMedium());
        entity.setUmtCampaign(link.getUtmTags().getUmtCampaign());
        entity.setUtmContent(link.getUtmTags().getUtmContent());

        entity.setUserId(link.getUser().getUserId());
        entity.setActive(link.isActive());
        entity.setExpirationDateTime(link.getExpirationDateTime());
        entity.setCreateAt(link.getCreateAt());
        entity.setUpdateAt(link.getUpdateAt());

        return entity;
    }

    public Link toDomain(LinkEntity link) {
        UtmTags tags = new UtmTags(link.getUtmSource(), link.getUtmMedium(), link.getUmtCampaign(), link.getUtmContent());
        User user= new User(link.getUserId());

        return  new Link(link.getLinkId(),
                link.getOriginalUrl(),
                tags,user,link.isActive(),
                link.getExpirationDateTime(),
                link.getCreateAt(),link.getUpdateAt());
    }


    class Constants {
        public static final String FK_USER_INDEX = "fk-user-index";
    }
}
