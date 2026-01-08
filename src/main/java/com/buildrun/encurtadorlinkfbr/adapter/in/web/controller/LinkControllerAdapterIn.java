package com.buildrun.encurtadorlinkfbr.adapter.in.web.controller;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.ShortenLinkResponse;
import com.buildrun.encurtadorlinkfbr.core.port.in.RedirectPortIn;
import com.buildrun.encurtadorlinkfbr.core.port.in.ShortenLinkPortIn;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("links")
@RestController
public class LinkControllerAdapterIn {

    private final ShortenLinkPortIn shortenLinkPortIn;
    private final RedirectPortIn redirectPortIn;

    public LinkControllerAdapterIn(ShortenLinkPortIn shortenLinkPortIn, RedirectPortIn redirectPortIn) {
        this.shortenLinkPortIn = shortenLinkPortIn;
        this.redirectPortIn = redirectPortIn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortenLinkResponse shortenLinks(@RequestBody @Valid ShortenLinkRequest request, JwtAuthenticationToken token){
        var userId = UUID.fromString( token.getToken().getSubject());

        return shortenLinkPortIn.execute(request.     toDomain(userId));
    }

    @GetMapping("{linkId}")
    public ShortenLinkResponse redirect(@PathVariable("linkId") String linkId){
        var fullUrl = redirectPortIn.execute(linkId);
        return null;
    }

}
