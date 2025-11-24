package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.User;
import com.buildrun.encurtadorlinkfbr.core.port.out.UserRepositoryPortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryPortOut {


    private final DynamoDbTable<UserEntity> dynamoDbTable;

    public UserDynamoDbAdapterOut(DynamoDbTable<UserEntity> dynamoDbTable) {
        this.dynamoDbTable = dynamoDbTable;
    }


    @Override
    public User save(User user) {

        var entity = UserEntity.fromDomain(user);
        dynamoDbTable.putItem(entity);

        return user;
    }


}
