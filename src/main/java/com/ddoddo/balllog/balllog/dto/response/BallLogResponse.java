package com.ddoddo.balllog.balllog.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogResponse(
    Long cheeringTeamId,
    Long opposingTeamId,
    Integer scoreCheering,
    Integer scoreOpposing,
    String title,
    String content,
    LocalDateTime matchDate,
    List<BallLogPhotoResponse> photos
) {
}
