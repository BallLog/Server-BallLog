package com.ddoddo.balllog.user.model;

public enum Status {
    ACTIVE(1, "활성 회원"),
    INACTIVE(2, "비활성 회원"),
    SUSPENDED(3, "정지된 회원"),
    BANNED(4, "차단된 회원"),
    WITHDRAWAL(100, "탈퇴한 회원");

    private int value;
    private String description;

    Status(int value, String description) {}
}
