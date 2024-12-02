package net.comicorp.collector.controller.impl;

import net.comicorp.collector.controller.UserController;
import net.comicorp.collector.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }
}
