package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.EntityTransaction;
import com.vitkovskaya.finalProject.dao.impl.CleanerDaoImpl;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.service.CleanerService;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CleanerServiceImpl implements CleanerService {


    private final static Logger logger = LogManager.getLogger();

    @Override
    public List<Cleaner> findAll() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();
        List<Cleaner> cleaners;
        transaction.beginNoTransaction(cleanerDao);
        try {
            cleaners = cleanerDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleaners;
    }

    @Override
    public Optional<Cleaner> findCleanerById(Long cleanerId) throws ServiceException {
        Optional<Cleaner> cleanerOptional;
        EntityTransaction transaction = new EntityTransaction();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();

        transaction.beginNoTransaction(cleanerDao);
        try {
            cleanerOptional = cleanerDao.findById(cleanerId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleanerOptional;
    }

    @Override
    public boolean registerCleaner(Long id, Map<String, String> parameters) throws ServiceException {
        Cleaner cleaner = new Cleaner();
        EntityTransaction transaction = new EntityTransaction();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();
        boolean created;
        cleaner.setCleanerId(id);
        cleaner.setFirstName(parameters.get(ConstantName.PARAMETER_FIRST_NAME));
        cleaner.setLastName((parameters.get(ConstantName.PARAMETER_LAST_NAME)));
        cleaner.setAddress((parameters.get(ConstantName.PARAMETER_ADDRESS)));
        cleaner.setTelephoneNumber((parameters.get(ConstantName.PARAMETER_TELEPHONE_NUMBER)));
        transaction.beginNoTransaction(cleanerDao);
        try {
            created = cleanerDao.create(cleaner);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return created;
    }

    @Override
    public boolean updateCleaner(Cleaner cleaner) throws ServiceException {
        logger.log(Level.DEBUG, cleaner + " IN SERVICE");
        EntityTransaction transaction = new EntityTransaction();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();
        boolean created;
        transaction.beginNoTransaction(cleanerDao);
        try {
            created = cleanerDao.update(cleaner);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while updating cleaner", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return created;
    }

    @Override
    public List<Cleaner> findBlockedCleaners() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();
        List<Cleaner> cleaners;
        transaction.beginNoTransaction(cleanerDao);
        try {
            cleaners = cleanerDao.findBlockedCleaners();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return cleaners;
    }


}
