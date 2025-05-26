package com.ddoddo.balllog.kbo.model;

import com.ddoddo.balllog.global.entity.BaseTimeEntity;
import com.ddoddo.balllog.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class KboTeam extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sponsorName;

    private String teamName;

    private String mainColor;

    private String subColor;

    private String cheerSongLink;

    private String instagramUrl;

    private String youtubeUrl;

    // todo Image 연관관계 Mapping
    private Long imageId;

//    @OneToMany
//    private List<User> userList;

}
