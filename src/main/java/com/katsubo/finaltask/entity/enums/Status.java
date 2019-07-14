package com.katsubo.finaltask.entity.enums;

public enum Status {
    CREATED(1),
    RUNNING(2),
    DONE(3),
    FAILURE(4);

    private Integer fieldCode;

    private Status(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getFieldCode() {
        return fieldCode;
    }

    public static Status getStatus(Integer index){
        return Status.values()[index];
    }
}
