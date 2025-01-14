package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class AppleStrategy implements AuthStrategy{

    public User signUp(String provider, Object signupRequest) {
        return null;
    }

    @Override
    public User signIn(String providerAccessToken) {
        return null;
    }

    @Override
    public void withdraw(User member) {

    }
}
