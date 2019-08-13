package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService extends Service {
    /**
     * Find list of all users
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws ServiceException the service exception
     */
    User findById(Integer id) throws ServiceException;

    /**
     * Check if user exist in database
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isExist(String login) throws ServiceException;

    /**
     * Find user by login and password.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Save user
     *
     * @param user the user
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(User user) throws ServiceException;

    /**
     * Delete user by id
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
