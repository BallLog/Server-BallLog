package com.ddoddo.balllog.balllog.controller;

import com.ddoddo.balllog.balllog.dto.request.BallLogPatchRequest;
import com.ddoddo.balllog.balllog.dto.request.BallLogPostRequest;
import com.ddoddo.balllog.balllog.dto.response.BallLogResponse;
import com.ddoddo.balllog.balllog.service.BallLogService;
import com.ddoddo.balllog.global.response.DataResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ball-log")
@RequiredArgsConstructor
public class BallLogController {

    private final BallLogService ballLogService;

    @PostMapping("")
    public DataResponseDto<BallLogResponse> createBallLog(
            @RequestBody @Valid BallLogPostRequest ballLogRequest
    ) {
        return DataResponseDto.from(ballLogService.createBallLog(ballLogRequest));
    }

    @PatchMapping("/{id}")
    public DataResponseDto<BallLogResponse> updateBallLog(
            @PathVariable Long id,
            @Valid @RequestBody BallLogPatchRequest ballLogPatchRequest
    ) {
        return DataResponseDto.from(ballLogService.updateBallLog(id, ballLogPatchRequest));
    }
}
