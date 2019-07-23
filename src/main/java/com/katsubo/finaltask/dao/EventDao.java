package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.entity.enums.Theme;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventDao extends Dao<Event> {
    List<Event> read() throws DaoException;

    List<Event> readByName(String search) throws DaoException;

    List<Event> readByDate(Date search) throws DaoException;

    List<Event> readByTheme(Theme search) throws DaoException;
}
