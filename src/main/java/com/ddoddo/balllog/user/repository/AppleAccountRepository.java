package com.ddoddo.balllog.user.repository;

import com.ddoddo.balllog.user.model.AppleAccount;
import com.ddoddo.balllog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleAccountRepository extends JpaRepository<AppleAccount, String> {
    AppleAccount findByUser(User user);
}
