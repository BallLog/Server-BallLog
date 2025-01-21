package com.ddoddo.balllog.auth.service.strategy;

import com.ddoddo.balllog.infra.model.KakaoResource;
import com.ddoddo.balllog.infra.service.KakaoService;
import com.ddoddo.balllog.user.model.KakaoAccount;
import com.ddoddo.balllog.user.model.SocialType;
import com.ddoddo.balllog.user.model.User;
import com.ddoddo.balllog.user.model.UserRole;
import com.ddoddo.balllog.user.repository.KakaoAccountRepository;
import com.ddoddo.balllog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class KakaoStrategy implements AuthStrategy {

    private final KakaoService kakaoService;
    private final KakaoAccountRepository kakaoAccountRepository;
    private final UserRepository userRepository;

    @Override
    public User signIn(String providerAccessToken) {
        KakaoResource kakaoResource = kakaoService.getKaKaoAccountInfo(providerAccessToken);

        Optional<User> user = findUserById(kakaoResource.getId());
        return user.orElseGet(() -> signUp(kakaoResource));
    }

    private User signUp(KakaoResource kakaoResource) {
        User user = new User(kakaoResource.getId(), SocialType.KAKAO, UserRole.USER);
        User savedUser = userRepository.save(user);

        kakaoAccountRepository.save(
                KakaoAccount.builder()
                        .id(kakaoResource.getId())
                        .user(savedUser)
                        .build()
        );

        return savedUser;
    }

    private Optional<User> findUserById(String id) {
        return kakaoAccountRepository.findById(id).map(KakaoAccount::getUser);
    }

    @Override
    public void withdraw(User member) {

    }

}
