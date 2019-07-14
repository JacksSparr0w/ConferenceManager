package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Role;

import java.util.List;
import java.util.Map;

public interface UserDao extends Dao<User>{
    User read(String login, String password) throws DaoException;
    List<User> read() throws DaoException;

    Map<Integer, Role> readAllUserEvents(Integer userId) throws DaoException;
}
