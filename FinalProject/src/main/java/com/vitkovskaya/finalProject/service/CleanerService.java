package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Cleaner;

import java.util.List;
import java.util.Map;
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

    boolean registerCleaner(Long id, Map<String, String> parameters) throws ServiceException;

    boolean updateCleaner(Cleaner cleaner) throws ServiceException;

    List<Cleaner> findBlockedCleaners() throws ServiceException;

}
