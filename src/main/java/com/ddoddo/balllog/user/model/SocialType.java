package com.ddoddo.balllog.user.model;

import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.exception.UnsupportedException;

public enum SocialType {
    KAKAO("KAKAO", "카카오"),
    APPLE("APPLE", "애플");

    private String type;
    private String description;

    SocialType(String socialType, String description) {}

    public static SocialType fromString(String socialType) {
        for (SocialType st : SocialType.values()) {
            if (st.type.equalsIgnoreCase(socialType)) {
                return st;
            }
        }
        throw new UnsupportedException(ErrorCode.SOCIAL_TYPE_NOT_FOUND);
    }
}