package com.katsubo.finaltask.util.repair;

//only for userInfo because of edit userInfo
public interface Recover<T> {
    T recover(T entity);
}
