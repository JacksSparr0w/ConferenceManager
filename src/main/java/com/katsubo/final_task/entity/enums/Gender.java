package com.katsubo.final_task.entity.enums;

public enum Gender {
    NO_MATTER(1),
    MALE(2),
    FEMALE(3);


    private Integer fieldCode;

    private Gender(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getFieldCode() {
        return fieldCode;
    }

    public static Gender getGender(Integer index){
        return Gender.values()[index];
    }
}
