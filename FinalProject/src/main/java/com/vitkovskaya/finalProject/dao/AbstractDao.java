package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Entity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * The {@code AbstractDAO} class
 * is a superclass for other DAO classes,
 * provides access to the database.
 */
public abstract class AbstractDao<K, T extends Entity> {
    private static final Logger logger = LogManager.getLogger();
    protected Connection connection;

    /**
     * Gets all rows from the table which represents one of the entity and
     * returns them as a list of {@code Entity} objects
     *
     * @return a list contains {@code Entity}, or empty list, not null
     * @throws DaoException if a database access error occurs
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Entity} object that represents this id.
     *
     * @param id a id of the entity object
     * @return an Optional {@code Entity}, or Optional.empty() if id is not founded.
     * @throws DaoException if a database access error occurs
     */
    public abstract Optional<T> findById(K id) throws DaoException;

    /**
     * Inserts a new row that represents {@code Entity} object into the table,
     * returns true if {@code Entity} was added to database,
     * sets the auto generated id to this {@code Entity} object
     *
     * @param entity an {@code Entity} object
     * @return a {@code true} if the table has such row, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Updates a row in the table using with new values
     *
     * @param entity a new {@code Entity} object
     * @return a {@code true} if the table was updated, {@code false} otherwise
     * @throws DaoException if {@code DaoException} occurs (database access error) or
     */

    public abstract boolean update(T entity) throws DaoException;
      /**
     * Sets the connection.
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Closes the statement.
     *
     * @throws DaoException if SQLException occurs
     */
    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Statement can't be closed", e);
        }
    }
}

