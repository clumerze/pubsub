package com.intech.testing.subscriber.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intech.testing.pubsub.common.data.MessageDto;
import com.intech.testing.subscriber.configuration.TestSubscriberApplication;
import com.intech.testing.subscriber.repository.MessageRepository;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Setter
@SpringBootTest(classes = TestSubscriberApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class SubscriberControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MessageRepository messageRepository;
    MessageDto messageDto;

    @SneakyThrows
    private static String toJson(Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @BeforeEach
    void setUp() {
        this.messageDto = MessageDto.builder()
                .msisdn(1)
                .action(MessageDto.Action.PURCHASE)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    @SneakyThrows
    @Test
    void postMessage() {
        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(messageDto))
        )
                .andExpect(status().isCreated())
                .andDo(print());

        Mockito.verify(messageRepository)
                .save(messageDto);
    }
}