package com.katsubo.finaltask.util.repair;

public interface Recover<T> {
    T recover(T entity);
}
