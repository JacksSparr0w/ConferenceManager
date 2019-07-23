package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Event;

import java.util.List;

public interface EventService extends Service {
    List<Event> findAll() throws ServiceException;

    Event findById(Integer id) throws ServiceException;

    void save(Event event) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
