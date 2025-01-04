package net.comicorp.collector.service.impl;

import lombok.RequiredArgsConstructor;
import net.comicorp.collector.domain.model.User;
import net.comicorp.collector.domain.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static net.comicorp.collector.constant.Constants.ROLE_PREFIX;
import static net.comicorp.collector.constant.Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::setUser)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));
    }

    private org.springframework.security.core.userdetails.User setUser(User user) {
        List<SimpleGrantedAuthority> profiles = Optional
                .ofNullable(user.getProfiles())
                .orElse(Collections.emptySet())
                .stream()
                .map(profile -> new SimpleGrantedAuthority(ROLE_PREFIX + profile.getProfileName()))
                .toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), profiles);
    }
}
