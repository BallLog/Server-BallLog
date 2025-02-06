package com.ddoddo.balllog.balllog.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BallLogPhoto extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ball_log_id")
    private BallLog ballLog;

    private String imgUrl;

    private int sequence;

    private LocalDateTime deletedAt;
}
