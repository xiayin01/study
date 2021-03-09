package com.xy.common.mvc.enums;

public enum ResponseStatus {

    SUCCESS(0, "成功"),

    FAIL(1, "失败");

    private int status;
    private final String text;

    ResponseStatus(int status, String text) {
        this.status = status;
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
