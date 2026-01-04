package com.buildrun.encurtadorlinkfbr.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    private String linkId;
    private String originalUrl;
    private UtmTags utmTags;
    private User user;
    private boolean active;
    private LocalDateTime expirationDateTime;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
