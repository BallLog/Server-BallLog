package com.ddoddo.balllog.jwt.repository;

import com.ddoddo.balllog.jwt.entity.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {
    Optional<JwtRefreshToken> findFirstByUserId(Long userId);

    void deleteByUserId(Long userId);
}
