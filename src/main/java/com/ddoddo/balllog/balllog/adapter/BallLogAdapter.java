package com.ddoddo.balllog.balllog.adapter;

import com.ddoddo.balllog.balllog.adapter.dto.BallLogDto;
import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.MatchResult;
import com.ddoddo.balllog.balllog.repository.BallLogRepository;
import com.ddoddo.balllog.global.exception.EntityNotFoundException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    public BallLog findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.BALL_LOG_NOT_FOUND));
    }

    public Slice<BallLog> findByUser(User user, Pageable pageable) {
        return repository.findByUser(user, pageable);
    }

    public Slice<BallLog> findByUserAndMatchResult(User user, MatchResult matchResult, Pageable pageable) {
        return repository.findByUserAndMatchResult(user, matchResult, pageable);
    }

    public BallLog update(Long id, BallLogDto ballLogDto) {
        BallLog ballLog = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.BALL_LOG_NOT_FOUND));

        ballLog.update(ballLogDto);

        return repository.save(ballLog);
    }

    public void delete(BallLog ballLog) {
        repository.delete(ballLog);
    }

}
