package com.buildrun.encurtadorlinkfbr.adapter.in.web;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserDto;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.CreateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserControllerAdapterIn {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(CreateUserDto request){
        return new CreateUserResponse("1234", LocalDateTime.now());
    }

}
