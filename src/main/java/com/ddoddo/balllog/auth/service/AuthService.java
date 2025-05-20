package com.ddoddo.balllog.auth.service;

import com.ddoddo.balllog.auth.dto.response.SignInResponse;
import com.ddoddo.balllog.auth.service.strategy.AuthActionProvider;
import com.ddoddo.balllog.auth.service.strategy.AuthStrategy;
import com.ddoddo.balllog.jwt.service.JwtTokenService;
import com.ddoddo.balllog.user.model.SocialType;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthActionProvider authActionProvider;
    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    public SignInResponse signIn(String provider, String providerAccessToken) {
        SocialType socialType = SocialType.valueOf(provider.toUpperCase());
        AuthStrategy authStrategy = authActionProvider.getStrategy(socialType);

        User user = authStrategy.signIn(providerAccessToken);

        return SignInResponse.from(user.getStatus(), jwtTokenService.issueToken(user));
    }

    public String withdraw() {
        User user = userService.getUser();

        final AuthStrategy authStrategy = authActionProvider.getStrategy(user.getSocialType());

        authStrategy.withdraw(user);
        jwtTokenService.removeJwtOnAccountDeletion(user);
        user.withdraw();

        return "회원 탈퇴가 완료되었습니다.";
    }
}
