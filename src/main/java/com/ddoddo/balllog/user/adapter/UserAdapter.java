package com.ddoddo.balllog.user.adapter;

import com.ddoddo.balllog.global.exception.EntityNotFoundException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
