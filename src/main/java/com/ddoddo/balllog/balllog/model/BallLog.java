package com.ddoddo.balllog.balllog.model;

import com.ddoddo.balllog.kbo.model.KboTeam;
import com.ddoddo.balllog.kbo.model.Stadium;
import com.ddoddo.balllog.kbo.model.StadiumSeatInfo;
import com.ddoddo.balllog.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BallLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_seat_info_id")
    private StadiumSeatInfo stadiumSeatInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cheaaring_team_id")
    private KboTeam cheeringTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opposing_team_id")
    private KboTeam opposingTeam;

    private Integer scoreCheering;

    private Integer scoreOpposing;

    // todo: converter 구현
    @Enumerated(EnumType.STRING)
    private MatchResult matchResult;

    private String title;

    private String content;

    private LocalDateTime matchDate;

    @Builder.Default
    @OneToMany(mappedBy = "ballLog")
    private List<BallLogPhoto> photos = new ArrayList<>();

    private InfieldOutfield field;

    private Integer blockNumber;

    private Integer rowNumber;

    private HomeAway homeAway;

    private String companionName;

    @ManyToMany
    private List<User> companions;

    private LocalDateTime deletedAt;

}
