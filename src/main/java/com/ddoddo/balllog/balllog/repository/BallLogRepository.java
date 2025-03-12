package com.ddoddo.balllog.balllog.repository;

import com.ddoddo.balllog.balllog.model.BallLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallLogRepository extends JpaRepository<BallLog, Long> {
}
