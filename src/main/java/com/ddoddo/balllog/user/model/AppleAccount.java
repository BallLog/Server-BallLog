package com.ddoddo.balllog.user.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppleAccount extends BaseTimeEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne
    @JoinColumn
    private User user;
}