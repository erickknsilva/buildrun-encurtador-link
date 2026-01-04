package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.domain.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShortenLinkRequest(
        @NotBlank(message = "Insira a url original")
        String originalUrl,
        @NotBlank(message = "Insira o link slug")
        String uniqueLinkSlug,
        UtmTagsReq utm,
        LocalDateTime expirationDateTime

) {

    public Link toDomain(UUID userId){
        return new Link(uniqueLinkSlug,
                originalUrl,
                utm.toDomain(),
               new User(userId),
                true,
                expirationDateTime,
                LocalDateTime.now(),
                LocalDateTime.now()
                );
    }

}
