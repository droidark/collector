package xyz.krakenkat.collector.service;

import xyz.krakenkat.collector.dto.UserDTO;

public interface UserService {
    UserDTO signUp(UserDTO userDTO);
}
