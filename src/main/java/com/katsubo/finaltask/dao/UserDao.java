package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao extends Dao<User> {
    /**
     * Read user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws DaoException the dao exception
     */
    User read(String login, String password) throws DaoException;

    /**
     * Find integer.
     *
     * @param login the login
     * @return the integer
     * @throws DaoException the dao exception
     */
    Integer find(String login) throws DaoException;

    /**
     * Read list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> read() throws DaoException;
}
