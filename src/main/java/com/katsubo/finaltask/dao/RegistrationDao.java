package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Registration;

import java.util.List;

/**
 * The interface Registration dao.
 */
public interface RegistrationDao extends Dao<Registration> {
    /**
     * Read users on event list.
     *
     * @param eventId the event id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Registration> readUsersOnEvent(Integer eventId) throws DaoException;

    /**
     * Read user events list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Registration> readUserEvents(Integer userId) throws DaoException;

    /**
     * Read registration.
     *
     * @param eventId the event id
     * @param userId  the user id
     * @return the registration
     * @throws DaoException the dao exception
     */
    Registration read(Integer eventId, Integer userId) throws DaoException;
}
