package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.User;

import java.util.List;

public interface RegistrationService extends Service {
    List<User> findUsersOnEvent(Integer eventId) throws ServiceException;

    List<Event> findUserEvents(Integer userId) throws ServiceException;

    Registration read(Integer id) throws ServiceException;

    Integer save(Registration registration) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    Registration readByUserAndEvent(Integer eventId, Integer userId) throws ServiceException;
}
