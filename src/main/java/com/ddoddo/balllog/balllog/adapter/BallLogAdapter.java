package com.ddoddo.balllog.balllog.adapter;

import com.ddoddo.balllog.balllog.adapter.dto.BallLogDto;
import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.repository.BallLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BallLogAdapter {
    private final BallLogRepository repository;

    public BallLog save(BallLogDto ballLogDto) {
        return repository.save(BallLog.builder()
                .user(ballLogDto.user())
                .cheeringTeam(ballLogDto.cheeringTeam())
                .opposingTeam(ballLogDto.opposingTeam())
                .scoreCheering(ballLogDto.scoreCheering())
                .scoreOpposing(ballLogDto.scoreOpposing())
                .matchResult(ballLogDto.matchResult())
                .title(ballLogDto.title())
                .content(ballLogDto.content())
                .stadium(ballLogDto.stadium())
                .matchDate(ballLogDto.matchDate())
                .build()
        );
    }

}
