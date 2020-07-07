package com.intech.testing.subscriber.repository;


import com.intech.testing.pubsub.common.data.Message;
import com.intech.testing.pubsub.common.data.MessageDto;
import com.intech.testing.subscriber.repository.data.PurchaseMessageEntity;
import com.intech.testing.subscriber.repository.data.SubscriptionMessageEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MessageRepository {
    private final SessionFactory SESSION_FACTORY;
    private final Map<MessageDto.Action, Class<? extends Message>> ACTION_ENTITY_MAP;

    public MessageRepository(
            SessionFactory sessionFactory
    ) {
        SESSION_FACTORY = sessionFactory;
        ACTION_ENTITY_MAP = new EnumMap<>(MessageDto.Action.class);
        ACTION_ENTITY_MAP.put(MessageDto.Action.PURCHASE, PurchaseMessageEntity.class);
        ACTION_ENTITY_MAP.put(MessageDto.Action.SUBSCRIPTION, SubscriptionMessageEntity.class);
    }

    public void save(MessageDto messageDto) {
        SESSION_FACTORY.getCurrentSession()
                .save(
                        defineMessageEntityType(
                                messageDto.getAction()
                        )
                                .getName(),
                        messageDto
                );
    }

    public Optional<Message> getByAction(MessageDto.Action action, long msisdn) {
        return Optional.ofNullable(
                SESSION_FACTORY.getCurrentSession()
                        .find(defineMessageEntityType(action), msisdn)
        );
    }

    private Class<? extends Message> defineMessageEntityType(MessageDto.Action action) {
        return Optional.ofNullable(
                ACTION_ENTITY_MAP.get(action)
        )
                .orElseThrow(() -> MessageRepositoryException.unknownMessage(action));
    }

    static class MessageRepositoryException extends RuntimeException {
        public MessageRepositoryException(String message) {
            super(message);
        }

        public static MessageRepositoryException unknownMessage(MessageDto.Action action) {
            throw new MessageRepositoryException(action.name());
        }
    }
}
