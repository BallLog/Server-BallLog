package com.ddoddo.balllog.kbo.adapter;

import com.ddoddo.balllog.global.exception.EntityNotFoundException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.kbo.model.KboTeam;
import com.ddoddo.balllog.kbo.repository.KboTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KboTeamAdapter {
    private final KboTeamRepository kboTeamRepository;

    public KboTeam findById(Long id) {
        return kboTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.KBO_TEAM_NOT_FOUND));
    }
}
