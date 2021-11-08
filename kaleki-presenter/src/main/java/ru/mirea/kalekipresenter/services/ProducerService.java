package ru.mirea.kalekipresenter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mirea.kalekipresenter.models.Message;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void produce(Message message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("messages", message);
    }
}
