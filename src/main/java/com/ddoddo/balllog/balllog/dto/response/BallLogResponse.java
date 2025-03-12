package com.ddoddo.balllog.balllog.dto.response;

import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.MatchResult;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogResponse(
    Long cheeringTeamId,
    Long opposingTeamId,
    Integer scoreCheering,
    Integer scoreOpposing,
    String content,
    MatchResult matchResult,
    LocalDateTime matchDate,
    List<BallLogPhotoResponse> photos
) {

    public static BallLogResponse of(BallLog ballLog, List<BallLogPhotoResponse> photos) {
        return  BallLogResponse.builder()
                .cheeringTeamId(ballLog.getCheeringTeam().getId())
                .opposingTeamId(ballLog.getOpposingTeam().getId())
                .scoreCheering(ballLog.getScoreCheering())
                .scoreOpposing(ballLog.getScoreOpposing())
                .content(ballLog.getContent())
                .matchDate(ballLog.getMatchDate())
                .matchResult(ballLog.getMatchResult())
                .photos(photos)
                .build();
    }
}
