package com.ddoddo.balllog.user.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class KakaoAccount {
    @Id
    @Column
    String id;

    @Column(nullable = false)
    @Setter
    private String refreshToken;

    @OneToOne
    @JoinColumn
    private User user;

    @Builder
    public KakaoAccount(String id, String refreshToken, User user) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.user = user;
    }

}
