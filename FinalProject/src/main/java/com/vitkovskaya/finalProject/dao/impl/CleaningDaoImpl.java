package com.vitkovskaya.finalProject.dao.impl;

import com.vitkovskaya.finalProject.dao.AbstractDao;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.CleaningDao;
import com.vitkovskaya.finalProject.dao.TableColumn;
import com.vitkovskaya.finalProject.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDaoImpl} class
 * <p>
 * provides access to the tables 'cleanings', 'cleaning_in_order' in the database
 */
public class CleaningDaoImpl extends AbstractDao<Long, Cleaning> implements CleaningDao {
    private final static Logger logger = LogManager.getLogger();
    private final static String SQL_SELECT_ALL_CLEANINGS = "SELECT cleaning_id, cleaning_name," +
            " price_per_unit, cleaning_type, cleaning_description, cleaning_quantity, available_status, fk_cleaner_id" +
            " FROM cleaning";
    private final static String SQL_SELECT_CLEANING_BY_ID = "SELECT cleaning_id, cleaning_name," +
            " price_per_unit, cleaning_type, cleaning_description, cleaning_quantity, available_status, fk_cleaner_id" +
            " FROM cleaning  WHERE  cleaning_id= ?";
    private final static String SQL_CREATE_CLEANING = "INSERT INTO cleaning (cleaning_name, " +
            " price_per_unit, cleaning_type, cleaning_description, cleaning_quantity, available_status, fk_cleaner_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_CLEANING = "UPDATE cleaning SET cleaning_name= ?, price_per_unit= ?, " +
            " cleaning_type = ?, cleaning_description= ?, cleaning_quantity= ? WHERE cleaning_id= ?";
    private final static String SQL_UPDATE_CLEANING_STATUS = "UPDATE cleaning SET available_status= ? " +
            " WHERE cleaning_id= ?";
    private final static String SQL_SELECT_CLEANING_BY_CLEANER_ID = "SELECT cleaning_id, cleaning_name, price_per_unit, " +
            "cleaning_type, cleaning_description, cleaning_quantity, available_status, fk_cleaner_id" +
            " FROM cleaning WHERE fk_cleaner_id = ?";
    private final static String SQL_FIND_CLEANINGS_IN_ORDER_BY_ID = "SElECT  cleaning.cleaning_id," +
            " cleaning.cleaning_name, cleaning.price_per_unit, cleaning.cleaning_type,  cleaning.cleaning_description, " +
            " cleaning.cleaning_quantity, cleaning.available_status, cleaning.fk_cleaner_id, " +
            "cleaning_in_order.items_in_order FROM cleaning  JOIN cleaning_in_order " +
            " ON cleaning_in_order.cleaning_id = cleaning.cleaning_id WHERE  cleaning_in_order.orders_id = ?";

    @Override
    public List<Cleaning> findAll() throws DaoException {
        List<Cleaning> cleaningList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEANINGS);
            while (resultSet.next()) {
                Cleaning cleaning = extractCleaning(resultSet);
                cleaningList.add(cleaning);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleaningList;
    }

    @Override
    public Optional<Cleaning> findById(Long id) throws DaoException {
        Cleaning cleaning = new Cleaning();
        Optional<Cleaning> cleaningOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLEANING_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cleaning = extractCleaning(resultSet);
            }
            cleaningOptional = Optional.of(cleaning);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleaningOptional;
    }

    @Override
    public boolean create(Cleaning cleaning) throws DaoException {
        logger.log(Level.DEBUG, cleaning);
        boolean created = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_CLEANING, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cleaning.getName());
            statement.setBigDecimal(2, cleaning.getPrice());
            statement.setString(3, cleaning.getCleaningType().toString().toLowerCase());
            statement.setString(4, cleaning.getDescription());
            statement.setInt(5, cleaning.getQuantity());
            statement.setBoolean(6, cleaning.getIsAvailable());
            statement.setLong(7, cleaning.getCleanerId());
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
    public boolean update(Cleaning cleaning) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CLEANING);
            statement.setString(1, cleaning.getName());
            statement.setBigDecimal(2, cleaning.getPrice());
            statement.setString(3, cleaning.getCleaningType().toString().toLowerCase());
            statement.setString(4, cleaning.getDescription());
            statement.setInt(5, cleaning.getQuantity());
            statement.setLong(6, cleaning.getId());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    @Override
    public List<Cleaning> findByCleanerId(long cleaningId) throws DaoException {
        List<Cleaning> cleaningList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLEANING_BY_CLEANER_ID);
            statement.setLong(1, cleaningId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cleaning cleaning = extractCleaning(resultSet);
                cleaningList.add(cleaning);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return cleaningList;
    }

    @Override
    public boolean updateCleaningStatus(long cleaningId, boolean availableStatus) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CLEANING_STATUS);
            statement.setBoolean(1, availableStatus);
            statement.setLong(2, cleaningId);
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;

    }

    public List<CleaningItem> findCleaningsInOrder(Long orderId) throws DaoException {
        List<CleaningItem> itemList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_CLEANINGS_IN_ORDER_BY_ID);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cleaning cleaning = extractCleaning(resultSet);
                itemList.add(new CleaningItem(cleaning, resultSet.getInt(TableColumn.CLEANING_IN_ORDER_ORDER_ITEM)));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return itemList;

    }

    /**
     * Creates a new {@code Cleaning} object and
     * sets its values using {@code ResultSet}
     *
     * @param resultSet a {@code ResultSet} to build an object
     * @return a {@code Cleaning}
     */
    private Cleaning extractCleaning(ResultSet resultSet) throws SQLException {
        Cleaning cleaning = new Cleaning();
        cleaning.setId(resultSet.getLong(TableColumn.CLEANING_ID));
        cleaning.setName(resultSet.getString(TableColumn.CLEANING_NAME));
        cleaning.setPrice(resultSet.getBigDecimal(TableColumn.CLEANING_PRICE_PER_UNIT));
        cleaning.setCleaningType(CleaningType.valueOf(resultSet.getString(TableColumn.CLEANING_TYPE).toUpperCase()));
        cleaning.setDescription(resultSet.getString(TableColumn.CLEANING_DESCRIPTION));
        cleaning.setQuantity(resultSet.getInt(TableColumn.CLEANING_QUANTITY));
        cleaning.setAvailable(resultSet.getBoolean(TableColumn.CLEANING_IS_AVAILABLE));
        cleaning.setCleanerId(resultSet.getLong(TableColumn.CLEANING_CLEANER_ID));
        return cleaning;
    }
}
