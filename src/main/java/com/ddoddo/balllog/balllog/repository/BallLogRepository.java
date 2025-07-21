package com.ddoddo.balllog.balllog.repository;

import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.MatchResult;
import com.ddoddo.balllog.balllog.dto.projection.UserMatchStats;
import com.ddoddo.balllog.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BallLogRepository extends JpaRepository<BallLog, Long> {

    Slice<BallLog> findByUser(User user, Pageable pageable);

    Slice<BallLog> findByUserAndMatchResult(User user, MatchResult matchResult, Pageable pageable);

    @Query("""
        SELECT new com.ddoddo.balllog.balllog.dto.projection.UserMatchStats (
            COUNT(b),
            SUM(CASE WHEN b.matchResult = :win THEN 1 ELSE 0 END)
        )
        FROM BallLog b
        WHERE b.user = :user
        """)
    UserMatchStats countStatsByUser(
            @Param("user") User user,
            @Param("win") MatchResult win
    );
}
