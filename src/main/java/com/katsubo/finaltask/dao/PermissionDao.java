package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Permission;

import java.util.List;

/**
 * The interface Permission dao.
 */
public interface PermissionDao extends Dao<Permission> {
    /**
     * Read list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Permission> read() throws DaoException;

}
