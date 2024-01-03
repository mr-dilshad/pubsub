package com.example.pubsub.service;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MessageConsumer {

    public void consume(BasicAcknowledgeablePubsubMessage message) {
        Map<String, String> messageMap = message.getPubsubMessage().getAttributesMap();
        log.info("Message has been received {}", message.getPubsubMessage());
        message.ack();
    }
}
