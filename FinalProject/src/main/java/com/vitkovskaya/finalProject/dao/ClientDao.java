package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.Order;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    List<Client> findClientByLastName(String patternName) throws DaoException;
    List<Client> findClientByAddress(String patternAddress) throws DaoException;
    Optional <Client> findClientByTelephoneNumber(String telephoneNumber) throws DaoException;

    /**
     * Returns a List that has a {@code Client}.
     * List contains Client which isActive status is false.
     *
     * @return a List contains {@code Client} of blocked clients, or empty List, not null
     * @throws DaoException if a database access error occurs
     */
    List<Client> findBlockedClients() throws DaoException;


}
