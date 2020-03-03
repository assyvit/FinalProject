package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Cleaning;

import java.math.BigDecimal;
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

    List<Cleaning> findCleaningByCleanerId(long cleanerId) throws ServiceException;

    Optional<Cleaning> findCleaningById(long cleaningId) throws ServiceException;

    Optional<BigDecimal> getCleaningPrice(long cleaningId) throws ServiceException;

    boolean changeCleaningStatus(long cleaningId, boolean availableStatus) throws ServiceException;
    boolean updateCleaning(Cleaning cleaning) throws ServiceException;
}
