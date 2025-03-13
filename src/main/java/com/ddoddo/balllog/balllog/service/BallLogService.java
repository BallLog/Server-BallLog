package com.ddoddo.balllog.balllog.service;

import com.ddoddo.balllog.balllog.adapter.BallLogAdapter;
import com.ddoddo.balllog.balllog.adapter.BallLogPhotoAdapter;
import com.ddoddo.balllog.balllog.adapter.dto.BallLogDto;
import com.ddoddo.balllog.balllog.adapter.dto.BallLogPhotoDto;
import com.ddoddo.balllog.balllog.dto.request.BallLogPatchRequest;
import com.ddoddo.balllog.balllog.dto.request.BallLogPostRequest;
import com.ddoddo.balllog.balllog.dto.request.BallLogRequest;
import com.ddoddo.balllog.balllog.dto.response.BallLogPhotoResponse;
import com.ddoddo.balllog.balllog.dto.response.BallLogResponse;
import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.kbo.adapter.KboTeamAdapter;
import com.ddoddo.balllog.kbo.adapter.StadiumAdapter;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BallLogService {

    private final UserService userService;

    private final KboTeamAdapter kboTeamAdapter;
    private final StadiumAdapter stadiumAdapter;
    private final BallLogAdapter ballLogAdapter;
    private final BallLogPhotoAdapter ballLogPhotoAdapter;

    public BallLogResponse createBallLog(BallLogPostRequest request) {
        User user = userService.getUser();

        BallLogDto ballLogDto = createBallLogDto(user, request);
        BallLog ballLog = ballLogAdapter.save(ballLogDto);

        List<BallLogPhotoDto> photoDtoList = request.photos().stream()
                .map(photoRequest -> BallLogPhotoDto.of(ballLog, photoRequest.imgUrl(), photoRequest.sequence()))
                .toList();
        List<BallLogPhotoResponse> ballLogPhotoResponseList = ballLogPhotoAdapter.saveBallLogPhotos(photoDtoList);

        return BallLogResponse.of(ballLog, ballLogPhotoResponseList);
    }

    public BallLogResponse updateBallLog(Long id, BallLogPatchRequest request) {
        User user = userService.getUser();

        BallLogDto ballLogDto = createBallLogDto(user, request);
        BallLog ballLog = ballLogAdapter.update(id, ballLogDto);

        List<BallLogPhotoResponse> ballLogPhotoResponseList = Collections.emptyList();
        if (request.photos() != null && !request.photos().isEmpty()) {
            List<BallLogPhotoDto> photoDtoList = request.photos().stream()
                .map(photoRequest -> BallLogPhotoDto.of(ballLog, photoRequest.imgUrl(), photoRequest.sequence()))
                .toList();
            ballLogPhotoResponseList = ballLogPhotoAdapter.updateBallLogPhotos(ballLog.getId(), photoDtoList);
        }

        return  BallLogResponse.of(ballLog, ballLogPhotoResponseList);
    }

    private BallLogDto createBallLogDto(User user, BallLogRequest request) {
        return BallLogDto.of(
                user,
                kboTeamAdapter.findById(request.cheeringTeamId()),
                request.scoreCheering(),
                kboTeamAdapter.findById(request.opposingTeamId()),
                request.scoreOpposing(),
                request.title(),
                request.content(),
                stadiumAdapter.findById(request.stadiumId()),
                request.matchDate()
        );
    }


}
