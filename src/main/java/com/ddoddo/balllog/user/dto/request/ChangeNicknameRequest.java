package com.ddoddo.balllog.user.dto.request;

import com.ddoddo.balllog.global.util.validator.CustomSize;
import lombok.Builder;

@Builder
public record ChangeNicknameRequest(
        @CustomSize(max = 30, message = "닉네임은 30자 이하로 입력해주세요.")
        String name
) {
}
