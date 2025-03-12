package com.ddoddo.balllog.balllog.service;

import com.ddoddo.balllog.balllog.adapter.BallLogAdapter;
import com.ddoddo.balllog.balllog.adapter.BallLogPhotoAdapter;
import com.ddoddo.balllog.balllog.adapter.dto.BallLogDto;
import com.ddoddo.balllog.balllog.adapter.dto.BallLogPhotoDto;
import com.ddoddo.balllog.balllog.dto.request.BallLogRequest;
import com.ddoddo.balllog.balllog.dto.response.BallLogPhotoResponse;
import com.ddoddo.balllog.balllog.dto.response.BallLogResponse;
import com.ddoddo.balllog.balllog.model.BallLog;
import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import com.ddoddo.balllog.kbo.adapter.KboTeamAdapter;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BallLogService {

    private final UserService userService;

    private final KboTeamAdapter kboTeamAdapter;
    private final BallLogAdapter ballLogAdapter;
    private final BallLogPhotoAdapter ballLogPhotoAdapter;

    public BallLogResponse createBallLog(BallLogRequest request) {
        User user = userService.getUser();

        BallLogDto ballLogDto = BallLogDto.of(
                user,
                kboTeamAdapter.findById(request.cheeringTeamId()),
                request.scoreCheering(),
                kboTeamAdapter.findById(request.opposingTeamId()),
                request.scoreOpposing(),
                request.content(),
                request.matchDate()
        );
        BallLog ballLog = ballLogAdapter.save(ballLogDto);

        List<BallLogPhotoResponse> ballLogPhotoResponseList = new ArrayList<>();
        request.photos()
                .forEach(
                        ballLogPhotoRequest -> {
                            BallLogPhoto ballLogPhoto = ballLogPhotoAdapter.save(BallLogPhotoDto.of(ballLog, ballLogPhotoRequest.imgUrl(), ballLogPhotoRequest.sequence()));
                            ballLogPhotoResponseList.add(BallLogPhotoResponse.of(ballLogPhoto));
                        }
                );

        return BallLogResponse.of(ballLog, ballLogPhotoResponseList);
    }
}
