package com.example.pubsub.controller;

import com.example.pubsub.adapter.PubSubAdapter;
//import com.example.pubsub.subscription.SubscriptionBuilder;
import com.example.pubsub.service.MessagePublisher;
import com.example.pubsub.subscription.SubscriptionBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@Slf4j
public class WebAppController {

    @Autowired
    private MessagePublisher messagePublisher;
    @PostMapping(value = "/publishMessage", consumes = {"application/json", "application/xml","text/plain"}, produces = "application/json")
    public ResponseEntity<?> publishMessage(@RequestBody String message, @RequestHeader("Content-type") String contentType) {
        log.info("Request Headers : " + contentType);
        MessageHeaders messageHeaders = new MessageHeaders(Map.of(MessageHeaders.CONTENT_TYPE, contentType));
        messagePublisher.sendMessageToPubSub(message, messageHeaders);
        return new ResponseEntity<>("Message published successfully", HttpStatus.OK);

    }


//    @PostMapping("/createSubscription")
//    public ResponseEntity<?> createSusbcription(@RequestBody Map<String,String> filters){
//        try{
//            return new ResponseEntity<>(subscriptionBuilder.createSubscription(filters), HttpStatus.CREATED);
//        }catch (Exception ex){
//            return new ResponseEntity<>("Exception occured while creating the subscription: "+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
