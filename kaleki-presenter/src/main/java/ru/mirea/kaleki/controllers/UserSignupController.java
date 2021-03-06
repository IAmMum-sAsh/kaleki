package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.security.exeption.ConflictException;
import ru.mirea.kaleki.security.payload.UserDtoPayload;
import ru.mirea.kaleki.services.UserService;

/**
 * The type User signup controller.
 */
@Controller
@RequestMapping("/api/signup")
public class UserSignupController {

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Signup new user response entity.
     *
     * @param userDtoPayload the user dto payload
     * @return the response entity
     */
    @PostMapping("/worker")
    public ResponseEntity<UserDto> signupNewUser(@RequestBody UserDtoPayload userDtoPayload) {
        if (userService.findByEmail(userDtoPayload.getEmail()).isPresent())
            throw new ConflictException();

        User registeredUser = userService.registerNewUser(userDtoPayload, "ROLE_WORKER");

        return ResponseEntity.ok(new UserDto(registeredUser));
    }

}