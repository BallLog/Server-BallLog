package com.ddoddo.balllog.user.dto.response;

import com.ddoddo.balllog.user.model.User;
import lombok.Builder;

@Builder
public class FavoriteKboTeamResponse {
    private Long userId;
    private Long kboTeamId;
    private String kboTeamName;

    public static FavoriteKboTeamResponse from(User user) {
        return FavoriteKboTeamResponse.builder()
                .userId(user.getId())
                .kboTeamId(user.getKboTeam().getId())
                .kboTeamName(user.getKboTeam().getTeamName())
                .build();
    }
}
