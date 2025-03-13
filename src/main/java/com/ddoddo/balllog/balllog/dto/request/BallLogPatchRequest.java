package com.ddoddo.balllog.balllog.dto.request;

import com.ddoddo.balllog.global.util.validator.CustomSize;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BallLogPatchRequest(
    Long cheeringTeamId,

    Long opposingTeamId,

    Integer scoreCheering,

    Integer scoreOpposing,

    @CustomSize(max = 28, message = "볼로그 제목은 28자 이하로 입력해주세요.")
    String title,

    @CustomSize(max = 150, message = "볼로그 내용은 150자 이하로 입력해주세요.")
    String content,

    Long stadiumId,

    LocalDateTime matchDate,

    @Size(max = 4, message = "사진은 최대 4장까지 업로드할 수 있습니다.")
    List<BallLogPhotoRequest> photos
) implements BallLogRequest {

}
