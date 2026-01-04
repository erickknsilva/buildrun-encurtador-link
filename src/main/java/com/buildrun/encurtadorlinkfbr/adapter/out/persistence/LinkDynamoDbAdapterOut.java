package com.buildrun.encurtadorlinkfbr.adapter.out.persistence;

import com.buildrun.encurtadorlinkfbr.core.domain.Link;
import com.buildrun.encurtadorlinkfbr.core.port.out.LinkRepositoryPortOut;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

 @Component
public class LinkDynamoDbAdapterOut implements LinkRepositoryPortOut {

    private final DynamoDbTable<LinkEntity> linkEntityDynamoDbTable;

    public LinkDynamoDbAdapterOut(DynamoDbTable<LinkEntity> linkEntityDynamoDbTable) {
        this.linkEntityDynamoDbTable = linkEntityDynamoDbTable;
    }

    @Override
    public Link save(Link link) {
        var linkEntity = LinkEntity.fromDomain(link);
        linkEntityDynamoDbTable.putItem(linkEntity);

        return link;
    }


}
