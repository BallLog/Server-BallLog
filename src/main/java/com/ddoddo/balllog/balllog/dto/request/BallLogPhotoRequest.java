package com.ddoddo.balllog.balllog.dto.request;

import lombok.Builder;

@Builder
public record BallLogPhotoRequest(
    String imgUrl,
    Integer sequence
) {

}
