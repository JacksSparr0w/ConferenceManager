package com.katsubo.finaltask.util.repair;

/**
 * @param <T> interface for recover different objects
 */
//only for userInfo because of edit userInfo
public interface Recover<T> {
    /**
     * @param entity to recover
     * @return recovered entity
     */
    T recover(T entity);
}
