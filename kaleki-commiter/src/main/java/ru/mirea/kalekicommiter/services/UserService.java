package ru.mirea.kalekicommiter.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.mirea.kalekicommiter.entitys.User;
import ru.mirea.kalekicommiter.repositories.UserRepository;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "userId",  groupId = "message_user_id")
    public void consumeUserId(@Payload(required = false) long userId){
        System.out.println("Consuming the message: " + userId);
    }

    @KafkaListener(topics = "email",  groupId = "message_email")
    public void consumeEmail(@Payload(required = false) String email){
        System.out.println("Consuming the message: " + email);
    }

    @KafkaListener(topics = "user",  groupId = "message_user")
    public void consumeUser(@Payload(required = false) User user){
        System.out.println("Consuming the message: " + user.toString());
    }

}