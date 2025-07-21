package com.ddoddo.balllog.user.dto.response;

import com.ddoddo.balllog.user.model.User;
import lombok.Builder;

@Builder
public record MypageInfoResponse(
        Long id,
        Integer cheeringTeamId,
        String name,
        Integer winRate
) {
    public static MypageInfoResponse of(User user, Integer winRate) {
        return MypageInfoResponse.builder()
                .id(user.getId())
                .cheeringTeamId(user.getKboTeam().getId())
                .name(user.getName())
                .winRate(winRate)
                .build();
    }
}
