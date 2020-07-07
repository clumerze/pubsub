package com.intech.testing.subscriber.repository;

import com.intech.testing.pubsub.common.data.MessageDto;
import com.intech.testing.subscriber.configuration.TestSubscriberApplication;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Setter
@SpringJUnitConfig(classes = TestSubscriberApplication.class)
class MessageRepositoryTest {
    @Autowired
    MessageRepository messageRepository;

    @Test
    void save() {
        var expected = MessageDto.builder()
                .msisdn(1)
                .action(MessageDto.Action.PURCHASE)
                .timestamp(System.currentTimeMillis())
                .build();
        messageRepository.save(expected);

        var actual = messageRepository.getByAction(MessageDto.Action.PURCHASE, expected.getMsisdn());

        assertThat(actual.get())
                .isEqualToComparingFieldByField(expected);

        expected = MessageDto.builder()
                .msisdn(2)
                .action(MessageDto.Action.SUBSCRIPTION)
                .timestamp(System.currentTimeMillis())
                .build();
        messageRepository.save(expected);

        actual = messageRepository.getByAction(MessageDto.Action.SUBSCRIPTION, expected.getMsisdn());

        assertThat(actual.get())
                .isEqualToComparingFieldByField(expected);
    }
}