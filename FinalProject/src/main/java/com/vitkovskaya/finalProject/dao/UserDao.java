package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;

import java.util.Optional;


public interface UserDao {
    /**
     * Checks if the table 'users' contains a row with these login and password
     *
     * @param login    a user login that should be checked
     * @param password a password
     * @return a {@code true} if the table has such row, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    boolean findUserByLoginAndPassword(String login, String password) throws DaoException;

    // FIXME: 27.02.2020
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Checks if the table 'users' contains a row with login
     *
     * @param login a user login that should be checked
     * @return a {@code true} if the table has such row, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    boolean checkUserLogin(String login) throws DaoException;

    long getUserId(String login) throws DaoException;

    boolean changeActiveStatus(long id, boolean flag) throws DaoException;

    int getUserRoleId(String login) throws DaoException;

    boolean setUserAvatar(Long userId, String imagePath) throws DaoException;
}
