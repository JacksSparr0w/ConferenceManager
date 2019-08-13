package com.katsubo.finaltask.validate;

/**
 * The interface Validator.
 *
 * @param <T> the type parameter
 */
public interface Validator<T> {
    /**
     * Is valid string.
     *
     * @param entity the entity
     * @return the string
     */
    String isValid(T entity);
}
