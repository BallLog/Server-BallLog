package com.ddoddo.balllog.jwt.repository;

import com.ddoddo.balllog.jwt.model.JwtRefreshToken;
import com.ddoddo.balllog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {
    Optional<JwtRefreshToken> findFirstByUserId(Long userId);
    Optional<JwtRefreshToken> findByUser(User user);

    void deleteByUserId(Long userId);
}
