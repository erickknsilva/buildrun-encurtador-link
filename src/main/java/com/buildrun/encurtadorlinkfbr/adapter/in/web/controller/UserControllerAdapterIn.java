package com.buildrun.encurtadorlinkfbr.adapter.in.web;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserResponse;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.DeleteRequest;
import com.buildrun.encurtadorlinkfbr.core.port.in.CreateUserPortIn;
import com.buildrun.encurtadorlinkfbr.core.port.in.DeleteUserPortIn;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllerAdapterIn {

    private final CreateUserPortIn createUserPortIn;
    private final DeleteUserPortIn deleteUserPortIn;

    public UserControllerAdapterIn(CreateUserPortIn createUserPortIn, DeleteUserPortIn deleteUserPortIn) {
        this.createUserPortIn = createUserPortIn;
        this.deleteUserPortIn = deleteUserPortIn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {

        var userCreated = createUserPortIn.execute(request.toDomain());
        return CreateUserResponse.fromDomain(userCreated);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String authRoute(JwtAuthenticationToken token){
        var tokenEmail = token.getTokenAttributes().get("email");

        return String.valueOf(tokenEmail);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public void deleteUser(JwtAuthenticationToken token,@RequestBody @Valid DeleteRequest request){
        deleteUserPortIn.execute(request.email());
    }

}
