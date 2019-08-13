package com.katsubo.finaltask.dao.impl;

import java.sql.Connection;

/**
 * The type Base dao.
 */
abstract public class BaseDaoImpl {
    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
