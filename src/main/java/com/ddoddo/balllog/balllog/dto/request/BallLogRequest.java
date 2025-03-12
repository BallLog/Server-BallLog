package com.ddoddo.balllog.balllog.dto.request;

import com.ddoddo.balllog.global.util.validator.CustomSize;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogRequest(
    @NotNull(message = "응원 팀 ID는 필수 입력 값입니다.")
    Long cheeringTeamId,

    @NotNull(message = "상대 팀 ID는 필수 입력 값입니다.")
    Long opposingTeamId,

    @NotNull(message = "응원 팀 점수는 필수 입력 값입니다.")
    Integer scoreCheering,

    @NotNull(message = "상대 팀 점수는 필수 입력 값입니다.")
    Integer scoreOpposing,

    @CustomSize(max = 150, message = "볼로그 내용은 150자 이하로 입력해주세요.")
    String content,

    LocalDateTime matchDate,

    List<BallLogPhotoRequest> photos
) {

}
