package com.ddoddo.balllog.user.service;

import com.ddoddo.balllog.kbo.model.KboTeam;
import com.ddoddo.balllog.kbo.service.KboTeamService;
import com.ddoddo.balllog.user.dto.request.FavoriteKboTeamRequest;
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

    public FavoriteKboTeamResponse selectFavoriteKboTeam(FavoriteKboTeamRequest requestDto) {
        KboTeam kboTeam = kboTeamService.getKboTeamById(requestDto.getKboTeamId());
        User user = userService.getUser();

        user.updateKboTeam(kboTeam);

        return FavoriteKboTeamResponse.from(user);
    }
}
