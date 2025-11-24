package com.buildrun.encurtadorlinkfbr.adapter.in.web;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserResponse;
import com.buildrun.encurtadorlinkfbr.core.port.in.CreateUserPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn) {
        this.createUserPortIn = createUserPortIn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {

        var userCreated = createUserPortIn.execute(request.toDomain());
        return CreateUserResponse.fromDomain(userCreated);
    }

}
