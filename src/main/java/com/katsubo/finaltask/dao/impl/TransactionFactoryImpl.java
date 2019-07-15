package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.connection.ConnectionPool;
import com.katsubo.finaltask.connection.PoolException;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private static final Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl() throws DaoException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

        } catch (SQLException | PoolException e) {
            logger.log(Level.ERROR, "Can't initialize transactions");
            throw new DaoException(e + "Can't initialize transactions");
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
