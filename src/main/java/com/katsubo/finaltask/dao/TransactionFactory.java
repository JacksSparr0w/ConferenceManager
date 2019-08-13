package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.dao.impl.TransactionImpl;

import java.sql.Connection;

/**
 * The interface Transaction factory.
 */
public interface TransactionFactory {
    /**
     * Gets transaction.
     *
     * @return the transaction
     */
    TransactionImpl getTransaction();

    /**
     * Gets connection.
     *
     * @return the connection
     */
    Connection getConnection();
}
