package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.EntityTransaction;
import com.vitkovskaya.finalProject.dao.impl.CleaningDaoImpl;
import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.CleaningType;
import com.vitkovskaya.finalProject.service.CleaningService;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CleaningServiceImpl implements CleaningService {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public boolean addCleaning(Map<String, String> map, long id) throws ServiceException {
        Cleaning cleaning = new Cleaning();
        cleaning.setName(map.get(ConstantName.PARAMETER_CLEANING_NAME));
        cleaning.setPrice(new BigDecimal(map.get(ConstantName.PARAMETER_CLEANING_PRICE)));
        cleaning.setCleaningType(CleaningType.valueOf(map.get(ConstantName.PARAMETER_CLEANING_TYPE).toUpperCase()));
        cleaning.setDescription(map.get(ConstantName.PARAMETER_CLEANING_DESCRIPTION));
        cleaning.setQuantity(Integer.valueOf(map.get(ConstantName.PARAMETER_CLEANING_QUANTITY)));
        cleaning.setAvailable(true);
        cleaning.setCleanerId(id);
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        boolean added;
        transaction.beginNoTransaction(cleaningDao);
        try {
            added = cleaningDao.create(cleaning);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while adding a cleaning", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return added;
    }
    @Override
    public List<Cleaning> findAllCleaning() throws ServiceException {
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<Cleaning> cleaningList = new ArrayList<>();
        transaction.beginNoTransaction(cleaningDao);
        try {
            cleaningList = cleaningDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while getting all cleanings", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleaningList;
    }
    @Override
    public List<Cleaning> findCleaningByCleanerId(long cleanerId) throws ServiceException {
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<Cleaning> cleaningList;
        transaction.beginNoTransaction(cleaningDao);
        try {
            cleaningList = cleaningDao.findByCleanerId(cleanerId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while getting cleaning", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleaningList;
    }
    @Override
    public Optional<Cleaning> findCleaningById(long cleaningId) throws ServiceException {
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        Optional<Cleaning> cleaningOptional;
        transaction.beginNoTransaction(cleaningDao);
        try {
            cleaningOptional = cleaningDao.findById(cleaningId);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while getting cleaning", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleaningOptional;
    }
    @Override
    public boolean changeCleaningStatus(long cleaningId, boolean availableStatus) throws ServiceException {
        boolean updated;
        EntityTransaction transaction = new EntityTransaction();
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        transaction.beginNoTransaction(cleaningDao);
        try {
            updated = cleaningDao.updateCleaningStatus(cleaningId, availableStatus);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while getting cleaning status", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }
    @Override
    public boolean updateCleaning(Cleaning cleaning) throws ServiceException {
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        boolean added = false;
        transaction.beginNoTransaction(cleaningDao);
        try {
            added = cleaningDao.update(cleaning);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while updating cleaning", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return added;
    }
   }
