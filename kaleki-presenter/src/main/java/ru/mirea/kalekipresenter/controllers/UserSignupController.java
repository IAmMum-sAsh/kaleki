package ru.mirea.kalekipresenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kalekipresenter.models.User;
import ru.mirea.kalekipresenter.security.dto.UserDto;
import ru.mirea.kalekipresenter.security.payload.UserDtoPayload;
import ru.mirea.kalekipresenter.services.UserService;

@Controller
@RequestMapping("/signup")
public class UserSignupController {

    @Autowired
    UserService userService;

    @PostMapping("/worker")
    public ResponseEntity<UserDto> signupNewUser(@RequestBody UserDtoPayload userDtoPayload) {
//        if (userService.findByEmail(userDtoPayload.getEmail()).isPresent())
//            throw new RuntimeException();

        User registeredUser = userService.produceNewUser(userDtoPayload, "ROLE_WORKER");

        return ResponseEntity.ok(new UserDto(registeredUser));
    }

    @PostMapping("/manager")
    public ResponseEntity<UserDto> signupNewManager(@RequestBody UserDtoPayload userDtoPayload) {
        if (userService.findByEmail(userDtoPayload.getEmail()).isPresent())
            throw new RuntimeException();


        User registeredUser = userService.registerNewUser(userDtoPayload, "ROLE_MANAGER");
        return ResponseEntity.ok(new UserDto(registeredUser));
    }

}