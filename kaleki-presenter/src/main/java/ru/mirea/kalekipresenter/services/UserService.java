package ru.mirea.kalekipresenter.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.kalekipresenter.models.Message;
import ru.mirea.kalekipresenter.models.User;
import ru.mirea.kalekipresenter.security.dto.UserDto;
import ru.mirea.kalekipresenter.security.payload.UserDtoPayload;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ProducerService producerService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Optional<User> produceUserId(long userId) {
        System.out.println("Отправляю: " + userId);
        kafkaTemplate.send("userId", userId);
        return Optional.of(new User());
    }

    public Optional<User> produceEmail(String email) {
        System.out.println("Отправляю: " + email);
        kafkaTemplate.send("email", email);
        return Optional.of(new User());
    }

    public void produceUser(User user) {
        System.out.println("Отправляю: " + user);
        kafkaTemplate.send("user", user);
    }

    public Optional<User> findById(long userId) {
        this.produceUserId(userId);
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent())
//            log.info("User " + optionalUser.get().toString() + "found by id " + userId);
//        else log.info("User with id '" + userId + "' not found.");
//        return userRepository.findById(userId);

        return Optional.of(new User());
    }

    public Optional<User> findByEmail(String email) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isPresent())
//            log.info("User " + optionalUser.get().toString() + "found by email " + email);
//        else log.info("User with email '" + email + "' not found.");
//        return userRepository.findByEmail(email);

        return Optional.of(new User());
    }

    public User registerNewUser(UserDtoPayload userDtoPayload, String role) {
        User user = new User();

        user.setEmail(userDtoPayload.getEmail());
        user.setRole(role);

        String encodedPassword = bCryptPasswordEncoder.encode(userDtoPayload.getPassword());
        user.setPassword(encodedPassword);

//        userRepository.save(user);

        return user;
    }

    public User produceNewUser(UserDtoPayload userDtoPayload, String role) {
        User user = new User();

        user.setEmail(userDtoPayload.getEmail());
        user.setRole(role);

        String encodedPassword = bCryptPasswordEncoder.encode(userDtoPayload.getPassword());
        user.setPassword(encodedPassword);

        System.out.println("Producing the message: " + user);
        kafkaTemplate.send("user", user);

//        userRepository.save(user);

        return user;
    }

}