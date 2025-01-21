package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.user.model.User;

public interface AuthStrategy {
//    User signUp(String providerAccessToken);
    User signIn(String providerAccessToken);
    void withdraw(User member);
}
