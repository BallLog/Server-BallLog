package com.ddoddo.balllog.auth.userdetails;

import com.ddoddo.balllog.user.model.SocialType;
import com.ddoddo.balllog.user.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class BallLogUserDetails implements UserDetails {

    private final User user;
    private final SocialType socialType;

    public BallLogUserDetails(User user) {
        this.user = user;
        this.socialType = user.getSocialType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
    }

    public String getSocialId() {
        return user.getSocialId();
    }

}