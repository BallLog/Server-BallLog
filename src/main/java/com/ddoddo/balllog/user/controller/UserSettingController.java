package com.ddoddo.balllog.user.controller;

import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.user.dto.request.ChangeNicknameRequest;
import com.ddoddo.balllog.user.dto.request.FavoriteKboTeamRequest;
import com.ddoddo.balllog.user.dto.response.ChangeNicknameResponse;
import com.ddoddo.balllog.user.dto.response.FavoriteKboTeamResponse;
import com.ddoddo.balllog.user.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserSettingController {

    private final UserSettingService userSettingService;

    @PostMapping("/my-kbo-team")
    public DataResponseDto<FavoriteKboTeamResponse> selectFavoriteKboTeam(
            @RequestBody FavoriteKboTeamRequest request
    ) {
        return DataResponseDto.from(userSettingService.selectFavoriteKboTeam(request));
    }

    @PatchMapping("/my-kbo-team")
    public DataResponseDto<FavoriteKboTeamResponse> changeFavoriteKboTeam(
            @RequestBody FavoriteKboTeamRequest request
    ) {
        return DataResponseDto.from(userSettingService.selectFavoriteKboTeam(request));
    }

    @PatchMapping("/nickname")
    public DataResponseDto<ChangeNicknameResponse> changeNickname(
            @RequestBody ChangeNicknameRequest request
    ) {
        return DataResponseDto.from(userSettingService.changeNickname(request));
    }
}
