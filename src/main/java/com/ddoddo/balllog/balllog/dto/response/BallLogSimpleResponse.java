package com.ddoddo.balllog.balllog.dto.response;

import com.ddoddo.balllog.balllog.model.BallLog;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BallLogSimpleResponse(
        String cheeringTeamName,
        String opposingTeamName,
        Integer scoreCheering,
        Integer scoreOpposing,
        String title,
        String content,
        LocalDateTime matchDate,
        String thumbnailUrl
) {

    public static BallLogSimpleResponse of(BallLog ballLog, String thumbnailUrl) {
        return BallLogSimpleResponse.builder()
                .cheeringTeamName(ballLog.getCheeringTeam().getTeamName())
                .opposingTeamName(ballLog.getOpposingTeam().getTeamName())
                .scoreCheering(ballLog.getScoreCheering())
                .scoreOpposing(ballLog.getScoreOpposing())
                .title(ballLog.getTitle())
                .content(ballLog.getContent())
                .matchDate(ballLog.getMatchDate())
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}
