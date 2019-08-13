package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.User;

import java.util.List;

/**
 * The interface Registration service.
 */
public interface RegistrationService extends Service {
    /**
     * Find users on event list.
     *
     * @param eventId the event id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersOnEvent(Integer eventId) throws ServiceException;

    /**
     * Find user events list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Event> findUserEvents(Integer userId) throws ServiceException;

    /**
     * Read registration.
     *
     * @param id the id
     * @return the registration
     * @throws ServiceException the service exception
     */
    Registration read(Integer id) throws ServiceException;

    /**
     * Save integer.
     *
     * @param registration the registration
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(Registration registration) throws ServiceException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Read by user and event registration.
     *
     * @param eventId the event id
     * @param userId  the user id
     * @return the registration
     * @throws ServiceException the service exception
     */
    Registration readByUserAndEvent(Integer eventId, Integer userId) throws ServiceException;
}
