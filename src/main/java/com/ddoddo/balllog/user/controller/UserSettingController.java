package com.ddoddo.balllog.user.controller;

import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.user.dto.request.FavoriteKboTeamRequest;
import com.ddoddo.balllog.user.dto.response.FavoriteKboTeamResponse;
import com.ddoddo.balllog.user.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserSettingController {

    private final UserSettingService userSettingService;

    @PostMapping("/my-kbo-team")
    public DataResponseDto<FavoriteKboTeamResponse> selectFavoriteKboTeam(@RequestBody FavoriteKboTeamRequest requestDto) {
        return DataResponseDto.from(userSettingService.selectFavoriteKboTeam(requestDto));
    }
}
