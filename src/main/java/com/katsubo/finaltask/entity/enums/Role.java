package com.katsubo.finaltask.entity.enums;

public enum Role {
    LISTENER(1),
    TELLER(2);

    private Integer fieldCode;

    private Role(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getFieldCode(){
        return fieldCode;
    }

    public static Role getRole(Integer index){
        return Role.values()[index];
    }
}
