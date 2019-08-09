package com.katsubo.finaltask.validate;

public interface Validator<T> {
    String isValid(T entity);
}
