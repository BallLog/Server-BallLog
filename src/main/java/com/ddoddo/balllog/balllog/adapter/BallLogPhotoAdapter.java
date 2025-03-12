package com.ddoddo.balllog.balllog.adapter;

import com.ddoddo.balllog.balllog.adapter.dto.BallLogPhotoDto;
import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import com.ddoddo.balllog.balllog.repository.BallLogPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BallLogPhotoAdapter {

    private final BallLogPhotoRepository repository;

    public BallLogPhoto save(BallLogPhotoDto photoDto) {
        return repository.save(BallLogPhoto.builder()
                .ballLog(photoDto.ballLog())
                .imgUrl(photoDto.imgUrl())
                .sequence(photoDto.sequence())
                .build()
        );
    }

}
