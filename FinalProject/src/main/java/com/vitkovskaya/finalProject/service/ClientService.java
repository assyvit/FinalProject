package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.Client;
import java.util.List;
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
     * Updates {@code Client} object -
     * updates database values - first name, last name, address, telephone number
     * for cleaner that should be edited.
     * After the update, updated values are set to this {@code Client} object
     *
     * @param client a {@code Client} that should be edited containing new values
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     * @return a {@code true} if (@code Client) was updated, {@code false} otherwise
     */

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
     Optional<Client> findByCleaningId(Long cleaningtId) throws ServiceException;
}
