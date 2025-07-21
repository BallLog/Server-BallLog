package com.ddoddo.balllog.user.service;

import com.ddoddo.balllog.balllog.adapter.BallLogAdapter;
import com.ddoddo.balllog.balllog.util.GameStatsUtils;
import com.ddoddo.balllog.global.exception.AuthorizationFailedException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.util.SecurityUtil;
import com.ddoddo.balllog.user.adapter.UserAdapter;
import com.ddoddo.balllog.user.dto.response.MypageInfoResponse;
import com.ddoddo.balllog.user.model.Status;
import com.ddoddo.balllog.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserAdapter userAdapter;
    private final BallLogAdapter ballLogAdapter;

    public User getUser() {
        Long userId = SecurityUtil.getUserId();
        User user = userAdapter.findById(userId);

        validateUserStatus(user);

        return user;
    }

    private void validateUserStatus(User user) {
        boolean isValidUser = user.getStatus().equals(Status.ACTIVE);
        if (Boolean.FALSE.equals(isValidUser)) {
            throw new AuthorizationFailedException(ErrorCode.INVALID_USER);
        }
    }

    public MypageInfoResponse getMypageInfo() {
        User user = getUser();
        Integer winRate = GameStatsUtils.calculateWinRate(ballLogAdapter.findUserMatchStats(user));

        return MypageInfoResponse.of(user, winRate);
    }
}
