package com.katsubo.finaltask.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ResourceBundle;

public class PoolServiceImpl implements PoolService {
    private static final Logger logger = LogManager.getLogger(PoolServiceImpl.class);
    private final String PROPERTY_PATH;
    private ConnectionPool pool;

    public PoolServiceImpl(String propertyPath) {
        PROPERTY_PATH = propertyPath;
        init();
    }

    private void init() {

    }

    @Override
    public Connection getConnection() throws PoolException {
        return pool.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) {
        pool.releaseConnection(connection);
    }
}
