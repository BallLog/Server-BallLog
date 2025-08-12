package com.ddoddo.balllog.user.service;

import com.ddoddo.balllog.global.exception.BusinessException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.kbo.model.KboTeam;
import com.ddoddo.balllog.kbo.service.KboTeamService;
import com.ddoddo.balllog.user.adapter.UserAdapter;
import com.ddoddo.balllog.user.dto.request.ChangeNicknameRequest;
import com.ddoddo.balllog.user.dto.request.FavoriteKboTeamRequest;
import com.ddoddo.balllog.user.dto.response.ChangeNicknameResponse;
import com.ddoddo.balllog.user.dto.response.FavoriteKboTeamResponse;
import com.ddoddo.balllog.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserSettingService {

    private final UserService userService;
    private final KboTeamService kboTeamService;

    private final UserAdapter userAdapter;

    public FavoriteKboTeamResponse selectFavoriteKboTeam(FavoriteKboTeamRequest requestDto) {
        KboTeam kboTeam = kboTeamService.getKboTeamById(requestDto.getKboTeamId());
        User user = userService.getUser();

        user.updateKboTeam(kboTeam);

        return FavoriteKboTeamResponse.from(user);
    }

    public ChangeNicknameResponse changeNickname(ChangeNicknameRequest requestDto) {
        validateNicknameUnique(requestDto.name());

        User user = userService.getUser();
        user.updateName(requestDto.name());

        return ChangeNicknameResponse.of(user);
    }

    private void validateNicknameUnique(String nickname) {
        if (userAdapter.existsByName(nickname)) {
            throw new BusinessException(ErrorCode.USER_NICKNAME_CAN_NOT_DUPLICATE);
        }
    }
}
