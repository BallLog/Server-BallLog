package com.ddoddo.balllog.balllog.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogRequest(
    Long cheeringTeamId,
    Long opposingTeamId,
    Integer scoreCheering,
    Integer scoreOpposing,
    String title,
    String content,
    LocalDateTime matchDate,
    List<BallLogPhotoRequest> photos
) {

}
