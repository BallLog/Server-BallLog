package com.ddoddo.balllog.user.controller;

import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.user.dto.response.MypageInfoResponse;
import com.ddoddo.balllog.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage-info")
    public DataResponseDto<MypageInfoResponse> getMypageInfo() {
        return DataResponseDto.from(userService.getMypageInfo());
    }

}
