package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Cleaner;
import java.util.List;
import java.util.Optional;

public interface CleanerDao {
    /**
     * Returns a List that has a {@code Cleaner}.
     * List contains Cleaner which isActive status is false.
     *
     * @return a List contains {@code Cleaner} of blocked cleaners, or empty List, not null
     * @throws DaoException if a database access error occurs
     */
    List<Cleaner> findBlockedCleaners() throws DaoException;
    /**
     * Search for a Cleaner defined by {@code Order} id
     *
     * @return a Optional {@code Cleaner}
     * @throws DaoException if a database access error occurs
     */
    Optional<Cleaner> findByOrderId(Long id) throws DaoException;
}
