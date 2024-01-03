package com.example.pubsub.adapter;

import com.example.pubsub.service.MessageConsumer;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
@Configuration
public class PubSubAdapter {

    @Value("${pubsub.subscription-name}")
    String Subscription;
    @Value("${pubsub.topic-name}")
    String Topic;

    //input channel
    @Bean("pubsubInputChannel")
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    //output channel
    @Bean("pubsubOutputChannel")
    public MessageChannel pubsubOutputChannel() {
        return new DirectChannel();
    }
    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, Subscription);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReceiver(MessageConsumer consumer) {
        return message -> {
            BasicAcknowledgeablePubsubMessage pubSubMessage =
                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            if(pubSubMessage!=null) {
                consumer.consume(pubSubMessage);
            }
        };
    }



    @Bean("defaultMessageHandler")
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        MessageHandler adapter = new PubSubMessageHandler(pubsubTemplate, Topic);
        return adapter;
    }

}
