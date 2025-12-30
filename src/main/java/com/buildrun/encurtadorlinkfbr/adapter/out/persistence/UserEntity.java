package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.User;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@DynamoDbBean
public class UserEntity {

    private UUID userId;

    private String email;

    private String password;

    private String nickName;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;




    public static UserEntity fromDomain(User user) {
        UserEntity entity = new UserEntity();

        entity.setUserId(user.getUserId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setNickName(user.getNickName());
        entity.setCreateAt(user.getCreateAt());
        entity.setUpdateAt(user.getUpdateAt());
        return entity;
    }

    public User toDomain() {
        return new User(
                this.userId, this.email, this.password, this.nickName, this.createAt, this.updateAt
        );
    }


    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public UUID getUserId() {
        return userId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "email-index")
    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    @DynamoDbAttribute("password")
    public String getPassword() {
        return password;
    }

    @DynamoDbAttribute("nick_name")
    public String getNickName() {
        return nickName;
    }

    @DynamoDbAttribute("create_at")
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @DynamoDbAttribute("update_at")
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }



}
