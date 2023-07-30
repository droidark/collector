package xyz.krakenkat.collector.service.impl;

import lombok.RequiredArgsConstructor;
import xyz.krakenkat.collector.domain.model.User;
import xyz.krakenkat.collector.domain.repository.UserRepository;
import xyz.krakenkat.collector.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::setUser)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE, username)));
    }

    private org.springframework.security.core.userdetails.User setUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getProfile())));
    }
}
