package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.User;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll() throws ServiceException;

    User findById(Integer id) throws ServiceException;

    boolean isExist(String login) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    Integer save(User user) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
