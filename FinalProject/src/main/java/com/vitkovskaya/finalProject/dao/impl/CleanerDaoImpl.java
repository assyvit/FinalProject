package com.vitkovskaya.finalProject.dao.impl;

import com.vitkovskaya.finalProject.dao.AbstractDao;
import com.vitkovskaya.finalProject.dao.CleanerDao;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.TableColumn;
import com.vitkovskaya.finalProject.entity.Cleaner;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDaoImpl} class
 * <p>
 * provides access to the tables 'cleaners' in the database
 */

public class CleanerDaoImpl extends AbstractDao<Long, Cleaner> implements CleanerDao {
    private final static Logger logger = LogManager.getLogger();
    private final static String SQL_SELECT_ALL_CLEANERS = "SELECT cleaner_id, first_name, last_name, telephone_number," +
            " address, active_status FROM cleaners LEFT JOIN users ON cleaner_id=users_id";
    private final static String SQL_CREATE_CLEANER = "INSERT INTO cleaners (cleaner_id, first_name, last_name," +
            " telephone_number, address) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_CLEANER = "UPDATE cleaners SET first_name= ?, last_name= ?, " +
            " telephone_number= ?, address= ? WHERE cleaner_id= ?";
    private final static String SQL_SELECT_BLOCKED_CLEANERS = "SELECT login, active_status, cleaner_id, first_name, " +
            " last_name, telephone_number, address FROM cleaners LEFT JOIN users ON users_id = cleaner_id WHERE " +
            "active_status = 0";
    private final static String SQL_SELECT_BY_CLEANER_ID = "SELECT cleaner_id, first_name, last_name, " +
            " telephone_number, address, active_status FROM cleaners LEFT JOIN users ON cleaner_id=users_id " +
            " WHERE cleaner_id =? AND fk_user_role = 2";
    private final static String SQL_SELECT_CLEANER_BY_ORDER_ID = "SELECT cleaners.cleaner_id, cleaners.first_name, " +
            " cleaners.last_name, cleaners.telephone_number,cleaners.address, cleaner_orders.cleaner_order_id " +
            " FROM cleaners LEFT JOIN  cleaner_orders ON cleaner_orders.cleaner_id = cleaners.cleaner_id " +
            " WHERE cleaner_orders.cleaner_order_id = ?";

    @Override
    public List<Cleaner> findBlockedCleaners() throws DaoException {
        List<Cleaner> cleanerList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BLOCKED_CLEANERS);
            while (resultSet.next()) {
                Cleaner cleaner = extractCleaner(resultSet);
                cleaner.setIsActive(resultSet.getBoolean(TableColumn.USER_STATUS));
                cleanerList.add(cleaner);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleanerList;
    }

    @Override
    public List<Cleaner> findAll() throws DaoException {
        List<Cleaner> cleanerList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEANERS);
            while (resultSet.next()) {
                Cleaner cleaner = extractCleaner(resultSet);
                cleaner.setIsActive(resultSet.getBoolean(TableColumn.USER_STATUS));
                cleanerList.add(cleaner);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleanerList;
    }

    @Override
    public Optional<Cleaner> findById(Long id) throws DaoException {
        PreparedStatement statement = null;
        Cleaner cleaner = null;
        Optional<Cleaner> cleanerOptional;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_CLEANER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cleaner = extractCleaner(resultSet);
            }
            cleanerOptional = Optional.ofNullable(cleaner);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleanerOptional;
    }

    @Override
    public Optional<Cleaner> findByOrderId(Long id) throws DaoException {
        PreparedStatement statement = null;
        Cleaner cleaner = null;
        Optional<Cleaner> cleanerOptional;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ORDER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cleaner = extractCleaner(resultSet);
            }
            cleanerOptional = Optional.ofNullable(cleaner);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleanerOptional;
    }

    @Override
    public boolean create(Cleaner cleaner) throws DaoException {
        boolean created = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_CLEANER);
            statement.setLong(1, cleaner.getCleanerId());
            statement.setString(2, cleaner.getFirstName());
            statement.setString(3, cleaner.getLastName());
            statement.setString(4, cleaner.getTelephoneNumber());
            statement.setString(5, cleaner.getAddress());
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                created = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return created;
    }

    @Override
    public boolean update(Cleaner cleaner) throws DaoException {
        boolean updated;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CLEANER);
            statement.setString(1, cleaner.getFirstName());
            statement.setString(2, cleaner.getLastName());
            statement.setString(3, cleaner.getTelephoneNumber());
            statement.setString(4, cleaner.getAddress());
            statement.setLong(5, cleaner.getCleanerId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }
    /**
     * Creates a new {@code Cleaner} object and
     * sets its values using {@code ResultSet}
     *
     * @param resultSet a {@code ResultSet} to build an object
     * @return a {@code Cleaner}
     */
    private Cleaner extractCleaner(ResultSet resultSet) throws SQLException {
        Cleaner cleaner = new Cleaner();
        cleaner.setCleanerId(resultSet.getLong(TableColumn.CLEANERS_ID));
        cleaner.setFirstName(resultSet.getString(TableColumn.CLEANERS_FIRST_NAME));
        cleaner.setLastName(resultSet.getString(TableColumn.CLEANERS_LAST_NAME));
        cleaner.setTelephoneNumber(resultSet.getString(TableColumn.CLEANERS_TELEPHONE_NUMBER));
        cleaner.setAddress(resultSet.getString(TableColumn.CLEANERS_ADDRESS));
        return cleaner;
    }
}
