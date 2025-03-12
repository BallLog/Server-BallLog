package com.ddoddo.balllog.balllog.adapter.dto;

import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import com.ddoddo.balllog.balllog.model.MatchResult;
import com.ddoddo.balllog.kbo.model.KboTeam;
import com.ddoddo.balllog.user.model.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogDto(
    Long id,
    User user,
    KboTeam cheeringTeam,
    KboTeam opposingTeam,
    Integer scoreCheering,
    Integer scoreOpposing,
    MatchResult matchResult,
    String content,
    LocalDateTime matchDate,
    List<BallLogPhoto> photos
) {

    public static BallLogDto of(User user, KboTeam cheeringTeam, Integer scoreCheering, KboTeam opposingTeam, Integer scoreOpposing, String content, LocalDateTime matchDate) {
        return BallLogDto.builder()
                .user(user)
                .cheeringTeam(cheeringTeam)
                .scoreCheering(scoreCheering)
                .opposingTeam(opposingTeam)
                .scoreOpposing(scoreOpposing)
                .content(content)
                .matchDate(matchDate)
                .matchResult(MatchResult.fromScores(scoreCheering, scoreOpposing))
                .build();
    }
}
