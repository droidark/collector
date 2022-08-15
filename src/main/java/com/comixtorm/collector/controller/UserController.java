package com.comixtorm.collector.controller;

import com.comixtorm.collector.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    @PostMapping("/signup")
    public User createUser(@RequestBody @Valid User user) {
        return user;
    }
    // change password
}
