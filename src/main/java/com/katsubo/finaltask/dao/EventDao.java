package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Value;

import java.util.Date;
import java.util.List;

/**
 * The interface Event dao.
 */
public interface EventDao extends Dao<Event> {
    /**
     * Read list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Event> read() throws DaoException;

    /**
     * Read by name list.
     *
     * @param search the search
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Event> readByName(String search) throws DaoException;

    /**
     * Read by date list.
     *
     * @param search the search
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Event> readByDate(Date search) throws DaoException;

    /**
     * Read by theme list.
     *
     * @param search the search
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Event> readByTheme(Value search) throws DaoException;
}
