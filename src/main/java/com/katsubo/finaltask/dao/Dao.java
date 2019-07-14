package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Entity;

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws DaoException;

    Type read(Integer id) throws DaoException;

    void update(Type entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
