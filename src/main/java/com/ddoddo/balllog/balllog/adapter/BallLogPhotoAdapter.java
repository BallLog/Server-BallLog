package com.ddoddo.balllog.balllog.adapter;

import com.ddoddo.balllog.balllog.adapter.dto.BallLogPhotoDto;
import com.ddoddo.balllog.balllog.dto.response.BallLogPhotoResponse;
import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import com.ddoddo.balllog.balllog.repository.BallLogPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BallLogPhotoAdapter {

    private final BallLogPhotoRepository repository;

    public List<BallLogPhotoResponse> saveBallLogPhotos(List<BallLogPhotoDto> photoDtoList) {
        List<BallLogPhoto> photos =  photoDtoList.stream()
                .map(photoDto -> BallLogPhoto.builder()
                        .ballLog(photoDto.ballLog())
                        .imgUrl(photoDto.imgUrl())
                        .sequence(photoDto.sequence())
                        .build())
                .toList();

        List<BallLogPhoto> savedPhotos = repository.saveAll(photos);

        return savedPhotos.stream()
                .map(BallLogPhotoResponse::of)
                .toList();
    }

    public List<BallLogPhotoResponse> updateBallLogPhotos(Long ballLogId, List<BallLogPhotoDto> photoDtoList) {
        List<BallLogPhoto> existingPhotos = repository.findByBallLogId(ballLogId);

        if (!existingPhotos.isEmpty()) {
            repository.deleteAll(existingPhotos);
        }

        return saveBallLogPhotos(photoDtoList);
    }

    private BallLogPhoto save(BallLogPhotoDto photoDto) {
        return repository.save(BallLogPhoto.builder()
                .ballLog(photoDto.ballLog())
                .imgUrl(photoDto.imgUrl())
                .sequence(photoDto.sequence())
                .build()
        );
    }

}
