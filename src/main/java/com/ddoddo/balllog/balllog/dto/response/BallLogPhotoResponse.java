package com.ddoddo.balllog.balllog.dto.response;

import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import lombok.Builder;

@Builder
public record BallLogPhotoResponse(
    String imgUrl,
    Integer sequence
) {

    public static BallLogPhotoResponse of(BallLogPhoto ballLogPhoto) {
        return  BallLogPhotoResponse.builder()
                .imgUrl(ballLogPhoto.getImgUrl())
                .sequence(ballLogPhoto.getSequence())
                .build();
    }
}
