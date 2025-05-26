package com.ddoddo.balllog.balllog.dto.request;

import java.time.LocalDateTime;

public interface BallLogRequest {
    Integer cheeringTeamId();
    Integer opposingTeamId();
    Integer scoreCheering();
    Integer scoreOpposing();
    String title();
    String content();
    Long stadiumId();
    LocalDateTime matchDate();
}
