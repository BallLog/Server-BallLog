package com.ddoddo.balllog.balllog.repository;

import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.MatchResult;
import com.ddoddo.balllog.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallLogRepository extends JpaRepository<BallLog, Long> {

    Slice<BallLog> findByUser(User user, Pageable pageable);

    Slice<BallLog> findByUserAndMatchResult(User user, MatchResult matchResult, Pageable pageable);
}
