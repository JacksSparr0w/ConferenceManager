package com.katsubo.finaltask.entity.enums;

/**
 * The enum Permission.
 */
public enum Permission {
    /**
     * Administrator permission.
     */
    ADMINISTRATOR(1),
    /**
     * User permission.
     */
    USER(2);


    private Integer fieldCode;

    private Permission(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    /**
     * Gets field code.
     *
     * @return the field code
     */
    public Integer getFieldCode() {
        return fieldCode;
    }

    /**
     * Get permission permission.
     *
     * @param index the index
     * @return the permission
     */
    public static Permission getPermission(Integer index){
        return Permission.values()[index];
    }
}
