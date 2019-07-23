package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws DaoException;

    Integer find(String login) throws DaoException;

    List<User> read() throws DaoException;
}
