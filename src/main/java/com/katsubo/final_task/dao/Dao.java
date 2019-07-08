package com.katsubo.final_task.dao;

import com.katsubo.final_task.entity.Entity;
import com.katsubo.final_task.exception.DaoException;

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws DaoException;

    Type read(Integer id) throws DaoException;

    void update(Type entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
