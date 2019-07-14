package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Role;

import java.util.List;
import java.util.Map;

public interface EventService extends Service {
    List<Event> findAll() throws ServiceException;

    Event findById(Integer id) throws ServiceException;

    Map<User, Role> findUsersWithRolesByEventId(Integer id) throws ServiceException;

    void save(Event event) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
