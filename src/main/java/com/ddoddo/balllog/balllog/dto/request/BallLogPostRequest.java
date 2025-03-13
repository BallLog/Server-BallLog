package com.ddoddo.balllog.balllog.dto.request;

import com.ddoddo.balllog.global.util.validator.CustomSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogPostRequest(
    @NotNull(message = "응원 팀 ID는 필수 입력 값입니다.")
    Long cheeringTeamId,

    @NotNull(message = "상대 팀 ID는 필수 입력 값입니다.")
    Long opposingTeamId,

    @NotNull(message = "응원 팀 점수는 필수 입력 값입니다.")
    Integer scoreCheering,

    @NotNull(message = "상대 팀 점수는 필수 입력 값입니다.")
    Integer scoreOpposing,

    @CustomSize(min = 1, max = 28, message = "볼로그 제목은 28자 이하로 입력해주세요.")
    String title,

    @CustomSize(min = 1, max = 150, message = "볼로그 내용은 150자 이하로 입력해주세요.")
    String content,

    @NotNull(message = "경기구장 ID는 필수 입력 값입니다.")
    Long stadiumId,

    LocalDateTime matchDate,

    @NotNull(message = "직관 사진은 최소 1장 이상 업로드 되어야 합니다.")
    @Size(min =1, max = 4, message = "사진은 최대 4장까지 업로드할 수 있습니다.")
    List<BallLogPhotoRequest> photos
) implements BallLogRequest{
}
