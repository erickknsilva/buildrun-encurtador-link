package com.buildrun.encurtadorlinkfbr.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID userId;
    private String email;
    private String password;
    private String nickName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public User(String email, String nickname, String password) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.nickName = nickname;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }



}
