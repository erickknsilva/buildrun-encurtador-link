package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.domain.User;
import com.buildrun.encurtadorlinkfbr.core.port.out.LinkRepositoryPortOut;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class LinkDynamoDbAdapterOut implements LinkRepositoryPortOut {

    private final DynamoDbTable<LinkEntity> dynamoDbTable;

    public LinkDynamoDbAdapterOut(DynamoDbTable<LinkEntity> linkEntityDynamoDbTable) {
        this.dynamoDbTable = linkEntityDynamoDbTable;
    }

    @Override
    public Link save(Link link) {
        var linkEntity = LinkEntity.fromDomain(link);
        dynamoDbTable.putItem(linkEntity);

        return link;
    }

    @Override
    public Optional<Link> findByOriginalUrl(String originalUrl) {
       var key = Key.builder()
               .partitionValue(originalUrl)
               .build();

       var entity = dynamoDbTable.getItem(key);

       return entity == null ? Optional.empty() : Optional.of(entity.toDomain(entity));
    }

    @Nonnull
    private static QueryEnhancedRequest getQueryEnhancedRequest(QueryConditional conditional) {
        return QueryEnhancedRequest.builder()
                .queryConditional(conditional)
                .build();
    }

}
