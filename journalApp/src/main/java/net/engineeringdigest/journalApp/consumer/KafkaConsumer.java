package net.engineeringdigest.journalApp.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

@KafkaListener(topics = {"gadegts"}, groupId = "gadegts-group")
    public void consumeMessage(String message) {
        System.out.println(message);
    }
}
