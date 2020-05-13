package com.xy.user.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {

    NORMAL(1, "普通用户"),
    VIP(2, "vip"),
    SUPER_VIP(3, "超级vip");

    private final int code;
    private final String text;

    UserTypeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static UserTypeEnum getUserTypeEnum(int code) {
        Optional<UserTypeEnum> optional = Arrays.stream(UserTypeEnum.values()).filter(userTypeEnum -> userTypeEnum.getCode() == code).findFirst();
        return optional.orElse(null);
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
