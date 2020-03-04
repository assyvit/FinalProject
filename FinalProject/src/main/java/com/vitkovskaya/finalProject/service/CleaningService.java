package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Cleaning;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CleaningService {

    boolean addCleaning(Map<String, String> map, long id) throws ServiceException;
    /**
     * Gets all cleanings from a database.
     *
     * @return a list contains {@code Cleaning}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Cleaning> findAllCleaning() throws ServiceException;
    /**
     * Gets all cleanings from a database of {@code Cleaner} defined by id.
     *
     * @param cleanerId {@code Cleaner} id whose cleaning to find
     * @return a list contains {@code Cleaning}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Cleaning> findCleaningByCleanerId(long cleanerId) throws ServiceException;
    /**
     * Gets a cleanings from a database of {@code Cleaner} defined by id.
     *
     * @param cleaningId {@code Cleaning} id to be found
     * @return a Optional {@code Cleaning} if object was found, otherwise Optional.empty
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Optional<Cleaning> findCleaningById(long cleaningId) throws ServiceException;
    /**
     * Updates {@code Cleaning} object with new value of available status.
     *
     * @param cleaningId an id of {@code Cleaning}  object to be updated
     * @param availableStatus value of new status
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     * @return a {@code true} if (@code Cleaning) was updated, {@code false} otherwise
     */
    boolean changeCleaningStatus(long cleaningId, boolean availableStatus) throws ServiceException;
    /**
     * Updates {@code Cleaning} object with new values of name, price, quantity, description, type.
     *
     * @param cleaning a {@code Cleaning} containing id and new values
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     * @return a {@code true} if (@code Cleaning) was updated, {@code false} otherwise
     */
        boolean updateCleaning(Cleaning cleaning) throws ServiceException;
}
