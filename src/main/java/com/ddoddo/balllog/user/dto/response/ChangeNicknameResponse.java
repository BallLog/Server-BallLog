package com.ddoddo.balllog.user.dto.response;

import com.ddoddo.balllog.user.model.User;
import lombok.Builder;

@Builder
public record ChangeNicknameResponse(
        Long id,
        String name
) {
    public static ChangeNicknameResponse of(User user) {
        return ChangeNicknameResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
