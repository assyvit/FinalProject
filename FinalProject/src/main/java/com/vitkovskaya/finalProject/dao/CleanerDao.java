package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Cleaner;


import java.util.List;

public interface CleanerDao {

    public List<Cleaner> findCleanerByLastName(String patternName) throws DaoException;

    public List<Cleaner> findCleanerByAddress(String patternAddress) throws DaoException;

    public List<Cleaner> findCleanerByTelephoneNumber(String telephoneNumber) throws DaoException;

    /**
     * Returns a List that has a {@code Cleaner}.
     * List contains Cleaner which isActive status is false.
     *
     * @return a List contains {@code Cleaner} of blocked cleaners, or empty List, not null
     * @throws DaoException if a database access error occurs
     */
    public List<Cleaner> findBlockedCleaners() throws DaoException;


}
