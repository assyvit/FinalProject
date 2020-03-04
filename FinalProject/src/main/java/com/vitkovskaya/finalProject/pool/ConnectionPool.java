package com.vitkovskaya.finalProject.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPool} class has private BlockingQueue in which
 * ProxyConnections are stored.
 * The max amount of created connections is set by DEFAULT_POOL_SIZE int value.
 * The connection can be taken from the BlockingQueue and
 * released to it. If the BlockingQueue is empty, and the amount of
 * created connections is less then POOL_SIZE, new connection
 * is created and returned, otherwise its needed to wait when a connection
 * is released (returned to current connection poll).
 * Thread safe.
 *
 * @see ProxyConnection
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_CONFIG = "connection.properties";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String URL_NAME = "URL";
    private static final int DEFAULT_POOL_SIZE = 20;
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> takenConnections;
    private Properties properties;
    private  static PropertyLoader propertyLoader = new PropertyLoader();
    private String URL;

    private ConnectionPool() {
        properties = propertyLoader.loadFiles(PATH_CONFIG);
        URL = properties.getProperty(URL_NAME);
        String DriverManagerPath = properties.getProperty(DRIVER_CLASS_NAME);
        try {
            Class.forName(DriverManagerPath);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Driver not found", e);
            throw new RuntimeException(e);
        }
        availableConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        takenConnections = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, properties);
                availableConnections.offer(new ProxyConnection(connection));
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error while creating connections", e);
            }
        }
        if (availableConnections.size() == 0) {
            logger.log(Level.FATAL, "Pool isn't created");
            throw new RuntimeException();
        }
        checkNumberConnections();
    }
    /**
     * Checks number of created connections, if number is less than default pool size create new,
     * while number of created connections is default pool size
     */
    private void checkNumberConnections() {
        int count = 0;
        if (getPoolSize() != DEFAULT_POOL_SIZE) {
            while (getPoolSize() != DEFAULT_POOL_SIZE) {
                count++;
                try {
                    Connection connection = DriverManager.getConnection(URL, properties);
                    availableConnections.offer(new ProxyConnection(connection));
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error while creating connections", e);
                }
            }
            logger.log(Level.ERROR, count + "Connections have been lost and created again");
        }
    }
    /**
     * Returns the ConnectionPool object.
     *
     * @return the {@code ConnectionPool} object.
     */
    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    /**
     * Returns the Connection object.
     *
     * @return the {@code Connection} object.
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            takenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection is lost");
            Thread.currentThread().interrupt();
        }
        return connection;
    }
    /**
     * Returns the Connection object to the {@code ConnectionPool} object .
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            takenConnections.remove(connection);
            availableConnections.offer((ProxyConnection) connection);
        }
    }
    private int getPoolSize() {
        return availableConnections.size() + takenConnections.size();
    }
    /**
     * Destroy the {@code ConnectionPool} object.
     */
    public void closePool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().realClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "ConnectionPool close error", e);
            }
        }
        deregisterDriver();
    }
    /**
     * Removes the specified driver from the {@code DriverManager}'s list of
     * registered drivers.
     */
    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, " DriverManager close error", e);
            }
        });
    }
}

