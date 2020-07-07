package com.intech.testing.subscriber.rest;

import com.intech.testing.pubsub.common.api.SubscriberApi;
import com.intech.testing.pubsub.common.data.MessageDto;
import com.intech.testing.subscriber.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
public class SubscriberController implements SubscriberApi {
    @Autowired
    private final MessageRepository REPOSITORY;

    public void post(MessageDto messageDto) {
        log.info(String.format("NEW_MESSAGE: %s ", messageDto));
        REPOSITORY.save(messageDto);
    }
}
