package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Client;
import java.util.List;
import java.util.Optional;

public interface ClientDao {
    /**
     * Returns a List that has a {@code Client}.
     * List contains Client which isActive status is false.
     *
     * @return a List contains {@code Client} of blocked clients, or empty List, not null
     * @throws DaoException if a database access error occurs
     */
    List<Client> findBlockedClients() throws DaoException;
    /**
     * Search for a {@code Client} defined by {@code Order} id
     *
     * @return a Optional {@code Client}
     * @throws DaoException if a database access error occurs
     */
    Optional<Client> findByOrderId(Long id) throws DaoException;
}
