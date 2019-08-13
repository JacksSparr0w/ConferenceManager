package com.katsubo.finaltask.entity.enums;

/**
 * The enum Role.
 */
public enum Role {
    /**
     * Listener role.
     */
    LISTENER(1),
    /**
     * Teller role.
     */
    TELLER(2),
    /**
     * Author role.
     */
    AUTHOR(3);

    private Integer fieldCode;

    private Role(Integer fieldCode) {
        this.fieldCode = fieldCode;
    }

    /**
     * Get field code integer.
     *
     * @return the integer
     */
    public Integer getFieldCode(){
        return fieldCode;
    }

    /**
     * Get role role.
     *
     * @param index the index
     * @return the role
     */
    public static Role getRole(Integer index){
        return Role.values()[index];
    }
}
