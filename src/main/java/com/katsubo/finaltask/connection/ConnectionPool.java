package com.katsubo.finaltask.connection;

import com.katsubo.finaltask.util.ResourceManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static AtomicReference<ConnectionPool> connectionPoolReference = new AtomicReference<>();
    private static final int INITIAL_CAPACITY = 15;
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private BlockingQueue<Connection> takenConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    private ConnectionPool() throws PoolException {

        try {
            lock.lock();
            if (connectionPoolReference.get() != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't register driver");
            throw new PoolException(e + "Cant' register driver");
        } finally {
            lock.unlock();
        }
        init();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        if (connectionPoolReference.get() == null) {
            try {
                lock.lock();
                if (connectionPoolReference.get() == null) {
                    connectionPoolReference.set(new ConnectionPool());
                }
            } catch (PoolException e) {
                logger.log(Level.ERROR, "Can't get instance", e);
                throw new RuntimeException("Can't get instance", e);
            } finally {
                lock.unlock();
            }
        }
        return connectionPoolReference.get();
    }

    private void init() throws PoolException{
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceManager.getProperty("path.database"));
        String connectionURL = resourceBundle.getString("db.url");
        String initialCapacityString = resourceBundle.getString("db.poolsize");
        Integer initialCapacity = Integer.valueOf(initialCapacityString);
        String login = resourceBundle.getString("db.user");
        String password = resourceBundle.getString("db.password");
        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = new PoolConnection(DriverManager.getConnection(connectionURL, login, password));
                freeConnections.add(connection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection can't initialize", e);
                throw new PoolException("Connection can't initialize", e);
            }
        }

    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws PoolException the pool exception
     */
    public Connection getConnection() throws PoolException {
        try {
            Connection connection = freeConnections.take();
            takenConnections.offer(connection);

            return connection;
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Can't get database", e);
            throw new PoolException("Can't get database", e);
        }
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        takenConnections.remove(connection);
        freeConnections.offer(connection);
    }

    /**
     * Destroy.
     */
    public void destroy() {
        freeConnections.addAll(takenConnections);
        takenConnections.clear();
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                PoolConnection connection = (PoolConnection) freeConnections.take();
                connection.realClose();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Connection close exception", e);
            }
        }
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.INFO, String.format("Deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.log(Level.ERROR, String.format("Error deregistering driver %s", driver), e);
            }
        }


    }

}
