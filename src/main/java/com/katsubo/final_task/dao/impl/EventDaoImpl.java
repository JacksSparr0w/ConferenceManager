package com.katsubo.final_task.dao.impl;

import com.katsubo.final_task.dao.EventDao;
import com.katsubo.final_task.entity.Event;
import com.katsubo.final_task.entity.User;
import com.katsubo.final_task.entity.enums.Role;
import com.katsubo.final_task.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventDaoImpl implements EventDao {
    @Override
    public List<Event> read() throws DaoException {
        return null;
    }

    @Override
    public Map<User, Role> readUserOnEvent() throws DaoException {
        return null;
    }

    @Override
    public List<Event> readByName(String search) throws DaoException {
        return null;
    }

    @Override
    public List<Event> readByDate(Date search) throws DaoException {
        return null;
    }

    @Override
    public Integer create(Event entity) throws DaoException {
        return null;
    }

    @Override
    public Event read(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(Event entity) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }
}
