package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.EntityTransaction;
import com.vitkovskaya.finalProject.dao.impl.ClientDaoImpl;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.service.ClientService;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public boolean registerClient(Long clientId, Map<String, String> parameters) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        Client client = new Client();
        client.setId(clientId);
        client.setFirstName(parameters.get(ConstantName.PARAMETER_FIRST_NAME));
        client.setLastName(parameters.get(ConstantName.PARAMETER_LAST_NAME));
        client.setAddress(parameters.get(ConstantName.PARAMETER_ADDRESS));
        client.setTelephoneNumber(parameters.get(ConstantName.PARAMETER_ADDRESS));
        boolean registered;
        transaction.beginNoTransaction(clientDao);
        try {
            registered = clientDao.create(client);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return registered;
    }

    @Override
    public boolean updateClient(Client client) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        boolean updated;
        transaction.beginNoTransaction(clientDao);
        try {
            updated = clientDao.update(client);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }

    @Override
    public List<Client> findBlockedClients() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        List<Client> clientList;
        transaction.beginNoTransaction(clientDao);
        try {
            clientList = clientDao.findBlockedClients();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return clientList;
    }

    @Override
    public Optional<Client> findById(Long clientId) throws ServiceException {
        Optional<Client> clientOptional;
        EntityTransaction transaction = new EntityTransaction();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        transaction.beginNoTransaction(clientDao);
        try {
            clientOptional = clientDao.findById(clientId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return clientOptional;

    }


    @Override
    public List<Client> findAllClients() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        List<Client> clientList;
        transaction.beginNoTransaction(clientDao);
        try {
            clientList = clientDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return clientList;
    }
}
