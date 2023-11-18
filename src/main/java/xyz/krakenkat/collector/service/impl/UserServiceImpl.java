package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.krakenkat.collector.domain.model.User;
import xyz.krakenkat.collector.domain.repository.UserRepository;
import xyz.krakenkat.collector.dto.UserDTO;
import xyz.krakenkat.collector.exception.FieldNotValidException;
import xyz.krakenkat.collector.service.UserService;
import xyz.krakenkat.collector.util.Constants;

import java.time.Instant;
import java.util.Date;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final Date signUpDate = Date.from(Instant.now());

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        if (!userRepository.existsByUsername(userDTO.getUsername().toLowerCase())) {
            if (!userRepository.existsByEmail(userDTO.getEmail().toLowerCase())) {
                return saveUser(userDTO);
            } else {
                throw new FieldNotValidException(Constants.EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE, userDTO.getEmail(), "email");
            }
        } else {
            throw new FieldNotValidException(Constants.USERNAME_ALREADY_EXISTS_EXCEPTION_MESSAGE, userDTO.getUsername(), "username");
        }
    }

    private UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(User
                .builder()
                .username(userDTO.getUsername().toLowerCase())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail().toLowerCase())
                .avatar(Constants.DEFAULT_AVATAR)
                .cover(Constants.DEFAULT_COVER)
                .aboutYou(Constants.DEFAULT_ABOUT_YOU)
                .signUpDate(signUpDate)
                .status(Constants.DEFAULT_STATUS)
                .profile(Constants.DEFAULT_PROFILE)
                .build());

        return UserDTO
                .builder()
                .username(savedUser.getUsername())
                .avatar(savedUser.getAvatar())
                .cover(savedUser.getCover())
                .aboutYou(savedUser.getAboutYou())
                .build();
    }
}
