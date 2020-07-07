package com.intech.testing.pubsub.common.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@NoArgsConstructor
@Data
@MappedSuperclass
public class Message {
    @Id
    private long msisdn;
    private long timestamp;
}