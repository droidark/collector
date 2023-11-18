package xyz.krakenkat.collector.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.dto.UserDTO;
import xyz.krakenkat.collector.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.signUp(userDTO);
    }
    // change password
}
