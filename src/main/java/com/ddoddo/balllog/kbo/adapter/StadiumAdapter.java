package com.ddoddo.balllog.kbo.adapter;

import com.ddoddo.balllog.global.exception.EntityNotFoundException;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.kbo.model.Stadium;
import com.ddoddo.balllog.kbo.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StadiumAdapter {
    private final StadiumRepository stadiumRepository;

    public Stadium findById(Long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STADIUM_NOT_FOUND));
    }
}
