package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.infra.model.AppleAccountInfo;
import com.ddoddo.balllog.infra.service.AppleService;
import com.ddoddo.balllog.user.model.AppleAccount;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.repository.AppleAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppleStrategy implements AuthStrategy{

    private final AppleService appleService;
    private final AppleAccountRepository appleAccountRepository;

    public User signUp(String provider, Object signupRequest) {
        return null;
    }

    @Override
    public User signIn(String providerAccessToken) {
        AppleAccountInfo appleAccountInfo = appleService.getAppleAccountInfo(providerAccessToken);

        // 회원가입이 안된 경우 처리해야함
        return  findUserById(appleAccountInfo.sub()).orElseThrow();

    }

    @Override
    public void withdraw(User member) {

    }

    public Optional<User> findUserById(String sub) {
        return appleAccountRepository.findById(sub).map(AppleAccount::getUser);
    }
}
