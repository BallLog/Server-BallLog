package com.ddoddo.balllog.balllog.dto.response;

import com.ddoddo.balllog.balllog.model.BallLog;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BallLogSimpleResponse(
        Long id,
        String cheeringTeamName,
        String opposingTeamName,
        Integer scoreCheering,
        Integer scoreOpposing,
        String title,
        String content,
        Integer stadiumId,
        LocalDateTime matchDate,
        String thumbnailUrl
) {

    public static BallLogSimpleResponse of(BallLog ballLog, String thumbnailUrl) {
        return BallLogSimpleResponse.builder()
                .id(ballLog.getId())
                .cheeringTeamName(ballLog.getCheeringTeam().getTeamName())
                .opposingTeamName(ballLog.getOpposingTeam().getTeamName())
                .scoreCheering(ballLog.getScoreCheering())
                .scoreOpposing(ballLog.getScoreOpposing())
                .title(ballLog.getTitle())
                .content(ballLog.getContent())
                .stadiumId(ballLog.getStadium().getId())
                .matchDate(ballLog.getMatchDate())
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}
