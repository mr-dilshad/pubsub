package com.example.pubsub.subscription;

import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubscriptionBuilder {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

//    @Value("${pubsub.topic-name}")
    String topicId="pubsubPoc";

//    @Value("${pubsub.subscription-name}")
    String subscriptionId="json-subscription";


//    public String createSubscription(Map<String, String> filters) throws IOException {
//        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
//
//            ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
//            ProjectSubscriptionName subscriptionName =
//                    ProjectSubscriptionName.of(projectId, subscriptionId);
//
//            if(filters.isEmpty()){
//                Subscription subscription =
//                        subscriptionAdminClient.createSubscription(
//                                Subscription.newBuilder()
//                                        .setName(subscriptionName.toString())
//                                        .setTopic(topicName.toString())
//                                        .build());
//
//                return "Created a subscription with filtering disabled: " + subscription.getAllFields();
//            }else{
//                String filterString = filters.entrySet()
//                        .stream()
//                        .map((entry) -> "attributes."+entry.getKey()+"="+"\""+entry.getValue()+"\"")
//                        .collect(Collectors.joining(","));
//                Subscription subscription =
//                        subscriptionAdminClient.createSubscription(
//                                Subscription.newBuilder()
//                                        .setName(subscriptionName.toString())
//                                        .setTopic(topicName.toString())
//                                        // Receive messages with attribute key "author" and value "unknown".
//                                        .setFilter(filterString)
//                                        .build());
//
//                return "Created a subscription with filtering enabled: " + subscription.getAllFields();
//            }
//
//
//
//        }
//    }
}