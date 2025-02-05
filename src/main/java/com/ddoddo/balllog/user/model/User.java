package com.ddoddo.balllog.user.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import com.ddoddo.balllog.kbo.model.KboTeam;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private KboTeam kboTeam;

    @Column(unique = true, nullable = false)
    private String socialId;

    private SocialType socialType;

    private String password;

    private String email;

    private String name;

    private String imageUrl;

    private int level;

    private Status status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(Long id, String socialId, SocialType socialType) {
        this.id = id;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public User(String socialId, SocialType socialType, UserRole role) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = role != null ? role : UserRole.USER;
        this.status = Status.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void updateKboTeam(KboTeam kboTeam) {
        this.kboTeam = kboTeam;
    }

}