package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Permission;

import java.util.List;

/**
 * The interface Permission service.
 */
public interface PermissionService extends Service {

    /**
     * Read list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Permission> readAll() throws ServiceException;

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
    Permission readById(Integer id) throws ServiceException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
