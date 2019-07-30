package com.katsubo.finaltask.validate;

public interface Validator<T> {
    boolean isValid(T entity);
}
