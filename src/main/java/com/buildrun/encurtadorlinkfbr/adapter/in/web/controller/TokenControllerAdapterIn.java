package com.buildrun.encurtadorlinkfbr.adapter.in.web.controller;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginResponse;
import com.buildrun.encurtadorlinkfbr.core.port.in.AuthenticatePortIn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth/token")
public class TokenControllerAdapterIn {

    private final AuthenticatePortIn authenticatePortIn;

    public TokenControllerAdapterIn(AuthenticatePortIn authenticatePortIn) {
        this.authenticatePortIn = authenticatePortIn;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request){
        return authenticatePortIn.execute(request);
    }


}
