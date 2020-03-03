package com.vitkovskaya.finalProject.dao.impl;

import com.vitkovskaya.finalProject.dao.AbstractDao;
import com.vitkovskaya.finalProject.dao.ClientDao;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.TableColumn;
import com.vitkovskaya.finalProject.entity.Client;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * The {@code OrderDaoImpl} class
 *
 * provides access to the tables 'clients' in the database
 */
public class ClientDaoImpl extends AbstractDao<Long, Client> implements ClientDao {
    private final static Logger logger = LogManager.getLogger();
    private final static String SQL_SELECT_ALL_CLIENTS = "SELECT client_id, first_name, last_name, " +
            "telephone_number, address FROM clients";
    private final static String SQL_SELECT_CLIENT_BY_ID = "SELECT client_id, first_name, clients.last_name, " +
            "telephone_number, address FROM clients WHERE client_id= ?";
    private final static String SQL_SELECT_CLIENT_BY_ADDRESS = "SELECT client_id, first_name, last_name, " +
            " telephone_number, address FROM clients WHERE address=?";
    private final static String SQL_SELECT_CLIENT_BY_LAST_NAME = "SELECT client_id, first_name, last_name, " +
            " telephone_number, address FROM clients WHERE last_name=?";
    private final static String SQL_SELECT_CLIENT_BY_TELEPHONE_NUMBER = "SELECT client_id, first_name, last_name, " +
            " telephone_number, address FROM clients WHERE telephone_number=?";
    private final static String SQL_CREATE_CLIENT = "INSERT INTO clients (client_id, first_name, last_name," +
            " telephone_number, address) VALUES (?, ?, ?, ?, ?)"; // FIXME: 21.01.2020
    private final static String SQL_UPDATE_CLIENT = "UPDATE clients SET first_name= ?, last_name= ?, " +
            "telephone_number= ?, address= ? WHERE client_id= ?"; // FIXME: 22.01.2020
    private final static String SQL_SELECT_BLOCK_CLIENTS = "SELECT login, active_status, client_id, first_name, " +
            "last_name, telephone_number, address FROM clients LEFT JOIN users  ON   users_id= client_id  WHERE active_status = 0";


    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientList;
    }

    @Override
    public Optional<Client> findById(Long id) throws DaoException {
        Client client = new Client();
        Optional<Client> clientOptional;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = extractClient(resultSet);
            }
            clientOptional = Optional.of(client);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientOptional;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        boolean created = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_CLIENT);
            statement.setLong(1, client.getId()); // FIXME: 22.01.2020  getUserId
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getTelephoneNumber());
            statement.setString(5, client.getAddress());
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 0) {
                created = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return created;
    }

    @Override
    public boolean update(Client client) throws DaoException {
        PreparedStatement statement = null;
        boolean updated;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_CLIENT);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getTelephoneNumber());
            statement.setString(4, client.getAddress());
            statement.setLong(5, client.getId());
          updated =  statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

//
//    @Override
//    public Client update(Client client) throws DaoException {
//        PreparedStatement statement = null;
//        Client oldClient;
//        //   oldClient = findById(client.getId()); // FIXME: 22.01.2020
//        try {
//            statement = connection.prepareStatement(SQL_UPDATE_CLIENT);
//            statement.setString(1, client.getFirstName());
//            statement.setString(2, client.getLastName());
//            statement.setString(3, client.getTelephoneNumber());
//            statement.setString(4, client.getAddress());
//            statement.setLong(5, client.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
//            throw new DaoException(e);
//        } finally {
//            close(statement);
//        }
//        // return oldClient;
//        return null;
//    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        return false;
    }

    @Override
    public List<Client> findClientByLastName(String patternName) throws DaoException {
        List<Client> clientList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_LAST_NAME);
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientList;
    }

    @Override
    public List<Client> findClientByAddress(String patternAddress) throws DaoException {
        List<Client> clientList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ADDRESS);
            statement.setString(1, patternAddress);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientList;
    }

    @Override
    public Optional<Client> findClientByTelephoneNumber(String telephoneNumber) throws DaoException {
        Client client = new Client();
        PreparedStatement statement = null;
        Optional<Client> clientOptional;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_TELEPHONE_NUMBER);
            statement.setString(1, telephoneNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = extractClient(resultSet);
            }
            clientOptional = Optional.of(client);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientOptional;
    }

    @Override
    public List<Client> findBlockedClients() throws DaoException {
        List<Client> clientList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BLOCK_CLIENTS);
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                client.setLogin(resultSet.getString(TableColumn.USER_LOGIN));
                client.setIsActive(resultSet.getBoolean(TableColumn.USER_STATUS));
                clientList.add(client);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return clientList;
    }


    private Client extractClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong(TableColumn.CLIENTS_CLIENT_ID));
        client.setFirstName(resultSet.getString(TableColumn.CLIENTS_FIRST_NAME));
        client.setLastName(resultSet.getString(TableColumn.CLIENTS_LAST_NAME));
        client.setTelephoneNumber(resultSet.getString(TableColumn.CLIENTS_TELEPHONE_NUMBER));
        client.setAddress(resultSet.getString(TableColumn.CLIENTS_ADDRESS));
        return client;
    }

}