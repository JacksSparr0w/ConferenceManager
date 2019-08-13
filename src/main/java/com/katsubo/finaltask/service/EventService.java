package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Event;

import java.util.List;

/**
 * The interface Event service.
 */
public interface EventService extends Service {
    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Event> findAll() throws ServiceException;

    /**
     * Find by id event.
     *
     * @param id the id
     * @return the event
     * @throws ServiceException the service exception
     */
    Event findById(Integer id) throws ServiceException;

    /**
     * Save integer.
     *
     * @param event the event
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(Event event) throws ServiceException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
