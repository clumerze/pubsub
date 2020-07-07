package com.intech.testing.publisher.main;

import com.intech.testing.publisher.service.MessageProducer;
import com.intech.testing.pubsub.common.api.SubscriberApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.spring.SpringContract;

public class PublisherApplication {
    public static void main(String[] args) {
        MessageProducer messageProducer = new MessageProducer();
        SubscriberApi publisher = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringContract())
                .target(SubscriberApi.class, "http://localhost:8080");

        for (int i = 0; i < 10; i++) {
            publisher.post(messageProducer.produce());
        }
    }
}
