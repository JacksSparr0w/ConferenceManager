package com.katsubo.finaltask.command.repair;

public interface Recover<T> {
    T recover(T entity);
}
