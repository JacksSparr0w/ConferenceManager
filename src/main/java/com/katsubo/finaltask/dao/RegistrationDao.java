package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Registration;

import java.util.List;

public interface RegistrationDao extends Dao<Registration> {
    List<Registration> readUsersOnEvent(Integer eventId) throws DaoException;

    List<Registration> readUserEvents(Integer userId) throws DaoException;

    Registration read(Integer eventId, Integer userId) throws DaoException;
}
