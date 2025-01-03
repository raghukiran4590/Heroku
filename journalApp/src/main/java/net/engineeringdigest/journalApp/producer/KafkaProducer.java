package net.engineeringdigest.journalApp.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public void sendMessage(@RequestParam String message) {
        kafkaTemplate.send("gadegts", message);
//        System.out.println("Message sent to Kafka");
    }
}
