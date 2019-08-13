package com.katsubo.finaltask.entity.enums;

/**
 * The enum Theme.
 */
public enum Theme {
    /**
     * Business theme.
     */
    BUSINESS(1),
    /**
     * Advertising theme.
     */
    ADVERTISING(2),
    /**
     * Science theme.
     */
    SCIENCE(3),
    /**
     * Design theme.
     */
    DESIGN(4);

    private Integer fieldCode;

    private Theme(int fieldCode){
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
     * Get theme theme.
     *
     * @param index the index
     * @return the theme
     */
    public static Theme getTheme(int index){
        return Theme.values()[index];
    }
}
