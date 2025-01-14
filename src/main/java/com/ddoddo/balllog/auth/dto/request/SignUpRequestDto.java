package com.ddoddo.balllog.auth.dto.request;

import com.ddoddo.balllog.user.model.SocialType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequestDto {
    SocialType socialType;
    Object data;
}
