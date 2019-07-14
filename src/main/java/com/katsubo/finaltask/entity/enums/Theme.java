package com.katsubo.finaltask.entity.enums;

public enum Theme {
    BUSINESS(1),
    ADVERTISING(2),
    SCIENCE(3),
    DESIGN(4);

    private Integer fieldCode;

    private Theme(int fieldCode){
        this.fieldCode = fieldCode;
    }

    public Integer getFieldCode(){
        return fieldCode;
    }

    public static Theme getTheme(int index){
        return Theme.values()[index];
    }
}
