package com.katsubo.finaltask.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final String PROPERTY_PATH = "/database.properties";
    //todo service for pool (service load database.property
    private static final int INITIAL_CAPACITY = 15;
    private static Lock lock = new ReentrantLock();
    private volatile static ConnectionPool connectionPool;
    private BlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private BlockingQueue<Connection> takenConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);
    private String login;
    private String password;
    private String connectionURL;
    private Integer initialCapacity;

    private ConnectionPool() throws SQLException {
        try {
            lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                initializeConnections();
            }
        } finally {
            lock.unlock();
        }
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            try {
                lock.lock();
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Can't get instance", e);
                throw new RuntimeException("Can't get instance", e);
            } finally {
                lock.unlock();
            }
        }
        return connectionPool;
    }

    private void initializeConnections() {
        /*
        //todo move to poolService
        Properties properties = new Properties();
        File file = new File(ConnectionPool.class.getResource(PROPERTY_PATH).getFile());
        try {
            properties.load(new FileReader(file));
            //todo
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading properties", e);
        }

        connectionURL = properties.getProperty("db.url");
        String initialCapacityString = properties.getProperty("db.poolsize");
        initialCapacity = Integer.valueOf(initialCapacityString);
        login = properties.getProperty("db.user");
        password = properties.getProperty("db.password");*/
        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = new PoolConnection(DriverManager.getConnection(connectionURL, login, password));
                freeConnections.add(connection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Pool can't initialize", e);
                throw new RuntimeException("Pool can't initialize", e);
            }
        }

    }

    public Connection getConnection() throws SQLException {
        try {
            Connection connection = freeConnections.take();
            takenConnections.offer(connection);

            return connection;
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Can't get database", e);
            throw new SQLException("Can't get database", e);
        }
    }

    public void releaseConnection(Connection connection) {
        takenConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public void destroy() {
        freeConnections.addAll(takenConnections);
        takenConnections.clear();
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                Connection connection = freeConnections.take();
                connection.close();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Connection close exception", e);
            }
        }
        //todo destroy all connections
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
