package com.ddoddo.balllog.balllog.repository;

import com.ddoddo.balllog.balllog.model.BallLogPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BallLogPhotoRepository extends JpaRepository<BallLogPhoto, Long> {
    List<BallLogPhoto> findByBallLogId(Long id);
}
