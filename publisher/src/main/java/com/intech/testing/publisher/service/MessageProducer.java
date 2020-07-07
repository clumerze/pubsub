package com.intech.testing.publisher.service;

import com.intech.testing.pubsub.common.data.MessageDto;

import java.util.Random;

public class MessageProducer {
    private final Random RANDOMIZER;
    private final MessageDto.Action[] ACTIONS;

    public MessageProducer() {
        RANDOMIZER = new Random();
        ACTIONS = MessageDto.Action.values();
    }

    public MessageDto produce() {
        return MessageDto.builder()
                .msisdn(
                        RANDOMIZER.nextLong()
                )
                .action(
                        ACTIONS[RANDOMIZER.nextInt(ACTIONS.length)]
                )
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
