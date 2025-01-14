package com.ddoddo.balllog.user.repository;

import com.ddoddo.balllog.user.model.KakaoAccount;
import com.ddoddo.balllog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoAccountRepository extends JpaRepository<KakaoAccount, String> {
    KakaoAccount findByUser(User user);
}
