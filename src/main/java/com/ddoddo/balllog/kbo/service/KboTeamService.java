package com.ddoddo.balllog.kbo.service;

import com.ddoddo.balllog.kbo.adapter.KboTeamAdapter;
import com.ddoddo.balllog.kbo.model.KboTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KboTeamService {
    private final KboTeamAdapter kboTeamAdapter;

    public KboTeam getKboTeamById(Long kboTeamId) {
        return kboTeamAdapter.findById(kboTeamId);
    }
}
