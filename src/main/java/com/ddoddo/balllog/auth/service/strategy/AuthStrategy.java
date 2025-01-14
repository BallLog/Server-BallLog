package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.user.model.User;

public interface AuthStrategy {
//    User signUp(String provider, Object signupRequest);
    User signIn(String providerAccessToken);
    void withdraw(User member);
}
