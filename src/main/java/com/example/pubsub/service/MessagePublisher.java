package com.example.pubsub.service;

import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessagePublisher {
    private final MessageHandler defaultMessageHandler;

    public void sendMessageToPubSub(String message, MessageHeaders messageHeaders){

        Message<ByteString> byteStringMessage = MessageBuilder.createMessage(ByteString.copyFromUtf8(message), messageHeaders);
        defaultMessageHandler.handleMessage(byteStringMessage);
    }

}
