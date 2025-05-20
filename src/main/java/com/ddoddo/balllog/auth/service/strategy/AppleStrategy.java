package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.infra.model.AppleAccountInfo;
import com.ddoddo.balllog.infra.model.AppleToken;
import com.ddoddo.balllog.infra.service.AppleService;
import com.ddoddo.balllog.user.model.AppleAccount;
import com.ddoddo.balllog.user.model.SocialType;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.model.UserRole;
import com.ddoddo.balllog.user.repository.AppleAccountRepository;
import com.ddoddo.balllog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppleStrategy implements AuthStrategy{

    private final AppleService appleService;
    private final AppleAccountRepository appleAccountRepository;
    private final UserRepository userRepository;

    @Override
    public User signIn(String providerAccessToken) {
        AppleAccountInfo appleAccountInfo = appleService.getAppleAccountInfo(providerAccessToken);
        Optional<User> user = findUserById(appleAccountInfo.sub());

        return user.orElseGet(() -> signUp(providerAccessToken));
    }

    private User signUp(String providerAccessToken) {
        AppleToken appleToken = appleService.getAppleToken(providerAccessToken);
        AppleAccountInfo appleAccountInfo = appleService.getAppleAccountInfo(appleToken.idToken());

        User user = new User(appleAccountInfo.sub(), SocialType.APPLE, UserRole.USER);
        User savedUser = userRepository.save(user);

        appleAccountRepository.save(
                AppleAccount.builder()
                        .id(appleAccountInfo.sub())
                        .user(savedUser)
                        .refreshToken(appleToken.refreshToken())
                        .build()
        );

        return savedUser;
    }

    public Optional<User> findUserById(String sub) {
        return appleAccountRepository.findById(sub).map(AppleAccount::getUser);
    }

    @Override
    public void withdraw(User user) {
        AppleAccount appleAccount = appleAccountRepository.findByUser(user);
        String appleRefreshToken = appleAccount.getRefreshToken();

        appleService.revokeAppleAccount(appleRefreshToken);
        appleAccountRepository.delete(appleAccount);

    }
}
