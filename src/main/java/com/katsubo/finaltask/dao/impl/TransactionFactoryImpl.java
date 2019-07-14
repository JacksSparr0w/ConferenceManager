package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.connection.ConnectionPool;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() throws DaoException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            //todo log can't set connection
            throw new DaoException(e);
        }


    }

    @Override
    public TransactionImpl getTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
