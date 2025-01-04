package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.comicorp.collector.component.KeyValidator;
import net.comicorp.collector.component.Mapper;
import net.comicorp.collector.constant.Status;
import net.comicorp.collector.domain.model.Profile;
import net.comicorp.collector.domain.model.User;
import net.comicorp.collector.domain.repository.ProfileRepository;
import net.comicorp.collector.domain.repository.UserRepository;
import net.comicorp.collector.dto.UserDTO;
import net.comicorp.collector.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static net.comicorp.collector.constant.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    private final KeyValidator keyValidator;

    private final Mapper mapper;


    @Override
    public UserDTO signUp(UserDTO userDTO) {

        keyValidator.validateKey(userDTO.getUsername(), () -> !userRepository.existsByUsername(userDTO.getUsername()), USERNAME_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        keyValidator.validateKey(userDTO.getEmail(), () -> !userRepository.existsByEmail(userDTO.getEmail()), EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE);

        Profile profile = profileRepository.findById(2L);

        User savedUser = userRepository.save(buildUser(userDTO, profile));
        profile.getUsers().add(savedUser);
        profileRepository.save(profile);

        return mapper.toUserDTO(savedUser);
    }

    private User buildUser(UserDTO userDTO, Profile profile) {
        return User
                .builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .avatar(DEFAULT_AVATAR)
                .cover(DEFAULT_COVER)
                .aboutYou(DEFAULT_ABOUT_YOU)
                .signUpDate(Date.from(Instant.now()))
                .profiles(Set.of(profile))
                .status(Status.PENDING)
                .build();
    }
}
