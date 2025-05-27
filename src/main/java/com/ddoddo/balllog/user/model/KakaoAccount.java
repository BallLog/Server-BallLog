package com.ddoddo.balllog.user.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class KakaoAccount extends BaseTimeEntity {
    @Id
    @Column
    String id;

//    todo: refreshToken 필수값인지 추후 확인 필요
//    @Column(nullable = false)
//    @Setter
//    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public KakaoAccount(String id, String refreshToken, User user) {
        this.id = id;
//        this.refreshToken = refreshToken;
        this.user = user;
    }

}
