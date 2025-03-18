package com.ddoddo.balllog.balllog.controller;

import com.ddoddo.balllog.balllog.dto.request.BallLogPatchRequest;
import com.ddoddo.balllog.balllog.dto.request.BallLogPostRequest;
import com.ddoddo.balllog.balllog.dto.response.BallLogFullResponse;
import com.ddoddo.balllog.balllog.dto.response.BallLogSimpleResponse;
import com.ddoddo.balllog.balllog.service.BallLogService;
import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.global.response.SliceResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ball-log")
@RequiredArgsConstructor
public class BallLogController {

    private final BallLogService ballLogService;

    @PostMapping("")
    public DataResponseDto<BallLogFullResponse> createBallLog(
            @RequestBody @Valid BallLogPostRequest ballLogRequest
    ) {
        return DataResponseDto.from(ballLogService.createBallLog(ballLogRequest));
    }

    @GetMapping("/{id}")
    public DataResponseDto<BallLogFullResponse> getBallLog(
            @PathVariable Long id
    ) {
        return DataResponseDto.from(ballLogService.getBallLog(id));
    }

    @GetMapping("")
    public DataResponseDto<SliceResponseDto<BallLogSimpleResponse>> getBallLogList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean onlyWin
    ) {
        return DataResponseDto.from(
                SliceResponseDto.from(ballLogService.getBallLogList(page, size, onlyWin))
        );
    }

    @PatchMapping("/{id}")
    public DataResponseDto<BallLogFullResponse> updateBallLog(
            @PathVariable Long id,
            @Valid @RequestBody BallLogPatchRequest ballLogPatchRequest
    ) {
        return DataResponseDto.from(ballLogService.updateBallLog(id, ballLogPatchRequest));
    }
}
