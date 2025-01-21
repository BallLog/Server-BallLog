package com.ddoddo.balllog.auth.service;

import com.ddoddo.balllog.auth.userdetails.BallLogUserDetails;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BallLogUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String socialId) throws UsernameNotFoundException {
        User user = userRepository.findBySocialId(socialId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with social Id: " + socialId));

        return new BallLogUserDetails(user);
    }
}
