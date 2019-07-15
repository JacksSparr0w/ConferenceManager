package com.katsubo.finaltask.connection;

import java.sql.Connection;

public interface PoolService {
    Connection getConnection() throws PoolException;
    void releaseConnection(Connection connection);


}
