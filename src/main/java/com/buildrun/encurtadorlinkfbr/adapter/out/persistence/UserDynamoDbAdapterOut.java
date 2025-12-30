package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.User;
import com.buildrun.encurtadorlinkfbr.core.port.out.UserRepositoryPortOut;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<User> findByEmail(String email) {

        var conditional = getQueryConditional(email);
        var query = getQueryEnhancedRequest(conditional);

        var emailIndex = dynamoDbTable.index("email-index");

        var results = emailIndex.query(query);

        return results.stream()
                .flatMap(p -> p.items().stream())
                .map(UserEntity::toDomain)
                .findFirst();

    }

    /**
     @Override public void deleteById(UUID uuid) {
     var key = Key.builder()
     .partitionValue(uuid.toString())
     .build();
     dynamoDbTable.deleteItem(key);
     }
     **/

    @Override
    public void deleteById(String email) {
        var userId = findByEmail(email).get().getUserId();

        var key = Key.builder().partitionValue(userId.toString()).build();
        dynamoDbTable.deleteItem(key);
    }

    @Override
    public Optional<User> findById(UUID userId) {

        var key = Key.builder()
                .partitionValue(userId.toString())
                .build();

        var entity = dynamoDbTable.getItem(key);
        return Optional.ofNullable(entity).map(UserEntity::toDomain);
    }


    @Nonnull
    private static QueryConditional getQueryConditional(String email) {
        return QueryConditional.keyEqualTo(
                k -> k.partitionValue(AttributeValue.builder().s(email)
                        .build()));

    }

    @Nonnull
    private static QueryEnhancedRequest getQueryEnhancedRequest(QueryConditional conditional) {
        return QueryEnhancedRequest.builder()
                .queryConditional(conditional)
                .build();
    }

}
