package com.intech.testing.subscriber.configuration;

import com.intech.testing.subscriber.main.SubscriberApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@Import(SubscriberApplication.class)
@Configuration
public class TestSubscriberApplication {
}
