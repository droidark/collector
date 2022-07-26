package xyz.krakenkat.collector.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.krakenkat.collector.dto.UserDTO;
import xyz.krakenkat.collector.service.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/signup")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.signUp(userDTO);
    }
    // change password
}
