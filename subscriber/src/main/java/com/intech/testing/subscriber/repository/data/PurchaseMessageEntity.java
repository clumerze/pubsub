package com.intech.testing.subscriber.repository.data;

import com.intech.testing.pubsub.common.data.Message;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity(name = "purchase")
@Table(name = "purchase")
public class PurchaseMessageEntity extends Message {
}
