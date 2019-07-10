package com.katsubo.final_task.dao;

import com.katsubo.final_task.entity.Event;
import com.katsubo.final_task.entity.enums.Role;
import com.katsubo.final_task.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventDao extends Dao<Event> {
    List<Event> read() throws DaoException;

    Map<Integer, Role> readUsersIdOnEventByEventId(Integer id) throws DaoException;

    List<Event> readByName(String search) throws DaoException;

    List<Event> readByDate(Date search) throws DaoException;
}
