package ru.mirea.kalekicommiter.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mirea.kalekicommiter.models.Message;

@Service
public class ConsumerService {

    @KafkaListener(topics = "messages",  groupId = "message_group_id")
    public void consume(Message message){
        System.out.println("Consuming the message: " + message);
    }
}