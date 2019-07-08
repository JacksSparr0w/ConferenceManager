package com.katsubo.final_task.dao;

import com.katsubo.final_task.entity.User;
import com.katsubo.final_task.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User>{
    User read(String login, String password) throws DaoException;
    List<User> read() throws DaoException;
}
