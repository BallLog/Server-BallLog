package com.ddoddo.balllog.balllog.dto.response;

import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.MatchResult;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogFullResponse(
    Long id,
    Integer cheeringTeamId,
    Integer opposingTeamId,
    Integer scoreCheering,
    Integer scoreOpposing,
    String title,
    String content,
    MatchResult matchResult,
    Integer stadiumId,
    LocalDateTime matchDate,
    Integer winRate,
    List<BallLogPhotoResponse> photos
) {

    public static BallLogFullResponse of(BallLog ballLog, List<BallLogPhotoResponse> photos, Integer winRate) {
        return  BallLogFullResponse.builder()
                .id(ballLog.getId())
                .cheeringTeamId(ballLog.getCheeringTeam().getId())
                .opposingTeamId(ballLog.getOpposingTeam().getId())
                .scoreCheering(ballLog.getScoreCheering())
                .scoreOpposing(ballLog.getScoreOpposing())
                .title(ballLog.getTitle())
                .content(ballLog.getContent())
                .matchDate(ballLog.getMatchDate())
                .stadiumId(ballLog.getStadium().getId())
                .matchResult(ballLog.getMatchResult())
                .winRate(winRate)
                .photos(photos)
                .build();
    }
}
