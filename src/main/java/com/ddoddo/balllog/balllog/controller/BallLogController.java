package com.ddoddo.balllog.balllog.controller;

import com.ddoddo.balllog.balllog.dto.request.BallLogRequest;
import com.ddoddo.balllog.balllog.dto.response.BallLogResponse;
import com.ddoddo.balllog.balllog.service.BallLogService;
import com.ddoddo.balllog.global.response.DataResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ball-log")
@RequiredArgsConstructor
public class BallLogController {

    private final BallLogService ballLogService;

    @PostMapping("")
    public DataResponseDto<BallLogResponse> createBallLog(@RequestBody @Valid BallLogRequest ballLogRequest) {

        return DataResponseDto.from(ballLogService.createBallLog(ballLogRequest));
    }
}
