package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClientService {

    /**
     * Gets all clients from a database.
     *
     * @return a list contains {@code Clients}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Client> findAllClients() throws ServiceException;


    /**
     * Creates {@code Client} object and
     * updates database with a new row that represents this object.
     * If the update is successful, returns {@code true} if the table has such row, {@code false} otherwise
     *
     * @param clientId    a client id
     * @param parameters  parameters to create client
     * @return a  {@code true} if the table has such row, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean registerClient(Long clientId, Map<String, String> parameters) throws ServiceException;

    boolean updateClient(Client client) throws ServiceException;
    /**
     * Gets all clients from a database with isActive statuse {@code false}.
     *
     * @return a list contains {@code Clients}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Client> findBlockedClients() throws ServiceException;
    /**
     * Gets (after finding and creating) a Optional {@code Client} object from a database using cleanerId.
     *
     * @param clientId a client id to find and create the Optional {@code Client} object
     * @return a founded and created Optional {@code Client} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Optional<Client> findById(Long clientId) throws ServiceException;
}
