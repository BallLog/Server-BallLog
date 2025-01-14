package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.user.model.SocialType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class AuthActionProvider {
    private final Map<SocialType, AuthStrategy> authActions;

    public AuthActionProvider(
            final KakaoStrategy kakaoStrategy,
            final AppleStrategy appleStrategy
    ) {
        this.authActions = new EnumMap<>(SocialType.class);
        this.authActions.put(SocialType.KAKAO, kakaoStrategy);
        this.authActions.put(SocialType.APPLE, appleStrategy);
    }

    public AuthStrategy getStrategy(SocialType socialType) {
        return authActions.get(socialType);
    }

}
