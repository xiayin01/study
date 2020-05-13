package com.xy.user.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {

    NORMAL(1, "normal", "普通用户"),
    VIP(2, "vip", "vip"),
    SUPER_VIP(3, "super_vip", "超级vip");

    private final int code;
    private final String value;
    private final String text;

    UserTypeEnum(int code, String value, String text) {
        this.code = code;
        this.value = value;
        this.text = text;
    }

    public static UserTypeEnum getUserTypeEnumByCode(int code) {
        Optional<UserTypeEnum> optional = Arrays.stream(UserTypeEnum.values()).filter(userTypeEnum -> userTypeEnum.getCode() == code).findFirst();
        return optional.orElse(null);
    }

    public static UserTypeEnum getUserTypeEnumByValue(String value) {
        Optional<UserTypeEnum> optional = Arrays.stream(UserTypeEnum.values()).filter(userTypeEnum -> userTypeEnum.getValue().equals(value)).findFirst();
        return optional.orElse(null);
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
