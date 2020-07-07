package com.intech.testing.subscriber.repository.data;

import com.intech.testing.pubsub.common.data.Message;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name = "subscription")
@Table(name = "subscription")
public class SubscriptionMessageEntity extends Message {
}
