package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Permission;

/**
 * The interface Permission service.
 */
public interface PermissionService extends Service {
    /**
     * Save integer.
     *
     * @param permission the permission
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(Permission permission) throws ServiceException;

    /**
     * Read permission.
     *
     * @param id the id
     * @return the permission
     * @throws ServiceException the service exception
     */
    Permission read(Integer id) throws ServiceException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
