package com.ddoddo.balllog.jwt.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import com.ddoddo.balllog.user.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class JwtRefreshToken extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public JwtRefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }
}