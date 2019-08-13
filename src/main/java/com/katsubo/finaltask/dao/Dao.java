package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Entity;

/**
 * The interface Dao.
 *
 * @param <Type> the type parameter
 */
public interface Dao<Type extends Entity> {
    /**
     * Create integer.
     *
     * @param entity the entity
     * @return the integer
     * @throws DaoException the dao exception
     */
    Integer create(Type entity) throws DaoException;

    /**
     * Read type.
     *
     * @param id the id
     * @return the type
     * @throws DaoException the dao exception
     */
    Type read(Integer id) throws DaoException;

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    void update(Type entity) throws DaoException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws DaoException the dao exception
     */
    void delete(Integer id) throws DaoException;
}
