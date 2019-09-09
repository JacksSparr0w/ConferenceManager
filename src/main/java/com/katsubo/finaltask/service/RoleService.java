package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Value;

import java.util.List;

/**
 * The interface Theme service.
 */
public interface RoleService extends Service {
    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Value> findAll() throws ServiceException;

    /**
     * Save integer.
     *
     * @param theme the theme
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(Value theme) throws ServiceException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
