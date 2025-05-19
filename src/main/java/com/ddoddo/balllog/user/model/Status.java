package com.ddoddo.balllog.user.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Status {
    ACTIVE(1, "활성 회원"),
    INACTIVE(2, "비활성 회원"),
    SUSPENDED(3, "정지된 회원"),
    BANNED(4, "차단된 회원"),
    WITHDRAWAL(100, "탈퇴한 회원");

    private int value;
    private String description;

    Status(int value, String description) {}

    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.value == value) {
                return status;
            }
        }
//        todo: Exception 공통 처리
        throw new IllegalArgumentException("Unknown Status value: " + value);
    }

    @Converter(autoApply = true) // 모든 Status Enum에 자동으로 적용 (선택 사항)
    public static class StatusConverter implements AttributeConverter<Status, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Status attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.getValue();
        }

        @Override
        public Status convertToEntityAttribute(Integer dbData) {
            if (dbData == null) {
                return null;
            }
            return Status.fromValue(dbData);
        }
    }
}
