package net.comicorp.collector.service;

import net.comicorp.collector.dto.UserDTO;

public interface UserService {
    UserDTO signUp(UserDTO userDTO);
}
