package com.intech.testing.pubsub.common.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class MessageDto extends Message {
    Action action;

    public enum Action {
        PURCHASE,
        SUBSCRIPTION
    }
}
