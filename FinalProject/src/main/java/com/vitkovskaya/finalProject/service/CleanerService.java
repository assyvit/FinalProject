package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Cleaner;

import java.util.List;
import java.util.Optional;

public interface CleanerService {
    /**
     * Gets all cleaners from a database.
     *
     * @return a list contains {@code Cleaner}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Cleaner> findAll() throws ServiceException;

    /**
     * Gets (after finding and creating) a Optional {@code Cleaner} object from a database using cleanerId.
     *
     * @param cleanerId a cleaner id to find and create the Optional {@code Cleaner} object
     * @return a founded and created Optional {@code Cleaner} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Optional<Cleaner> findCleanerById(Long cleanerId) throws ServiceException;

    /**
     * Updates {@code Cleaner} object -
     * updates database values - first name, last name, address, telephone number
     * for cleaner that should be edited.
     * After the update, updated values are set to this {@code Cleaner} object
     *
     * @param cleaner a {@code Cleaner} that should be edited containing new values
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     * @return a {@code true} if (@code Cleaner) was updated, {@code false} otherwise
     */
    boolean updateCleaner(Cleaner cleaner) throws ServiceException;

    /**
     * Gets all blocked cleaners from a database.
     *
     * @return a list contains {@code Cleaner}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Cleaner> findBlockedCleaners() throws ServiceException;

}
