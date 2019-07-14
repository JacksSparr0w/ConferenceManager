package com.katsubo.finaltask.dao;

public interface Transaction {
    UserDao getUserDao();

    EventDao getEventDao();

    UserInfoDao getUserInfoDao();

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
