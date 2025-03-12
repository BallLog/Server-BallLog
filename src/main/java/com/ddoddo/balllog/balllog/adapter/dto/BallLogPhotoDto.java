package com.ddoddo.balllog.balllog.adapter.dto;

import com.ddoddo.balllog.balllog.model.BallLog;
import lombok.Builder;

@Builder
public record BallLogPhotoDto(
    Long id,
    BallLog ballLog,
    String imgUrl,
    Integer sequence
) {

    public static BallLogPhotoDto of(BallLog ballLog, String imgUrl, Integer sequence) {
        return BallLogPhotoDto.builder()
                .ballLog(ballLog)
                .imgUrl(imgUrl)
                .sequence(sequence)
                .build();
    }
}
