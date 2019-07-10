package com.katsubo.final_task.entity.enums;

public enum Permission {
    ADMINISTRATOR(1),
    USER(2);


    private Integer fieldCode;

    private Permission(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getFieldCode() {
        return fieldCode;
    }

    public static Permission getPermission(Integer index){
        return Permission.values()[index];
    }
}
