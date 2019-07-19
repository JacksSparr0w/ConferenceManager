package com.katsubo.finaltask.command;

public enum Constances {
    ID("id"),
    ROLE("role"),
    USER("user"),
    INCLUDE("includeView"),
    USER_INFO("userInfo");

    private final String fieldName;

    private Constances(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
