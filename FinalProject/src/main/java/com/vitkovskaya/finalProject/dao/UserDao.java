package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.User;
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

    /**
     * Gets a row from the table using login,
     * builds and returns  optional {@code User} object that represents this login.
     *
     * @param login a login of the user object
     * @return a Optional {@code User}, or Optional.empty() if login is not founded.
     * @throws DaoException if a database access error occurs
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Checks if the table 'users' contains a row with login
     *
     * @param login a user login that should be checked
     * @return a {@code true} if the table has such row, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    boolean checkUserLogin(String login) throws DaoException;
    /**
     * Gets a row from the table using login,
     * and returns  id that represents this login.
     *
     * @param login a login of the user object
     * @return value of id if login is not founded, otherwise return 0.
     * @throws DaoException if a database access error occurs
     */
    long getUserId(String login) throws DaoException;
    /**
     * Gets a row from the table using id,
     * sets  active status value to {@code User} object that represents this id.
     *
     * @param id an id of the user object
     * @param activeStatus status to be set to the {@code User} object
     * @return a {@code true} if the table was updated, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    boolean changeActiveStatus(long id, boolean activeStatus) throws DaoException;
    /**
     * Gets row from the table using login
     *
     * @param login a user login of the user object
     * @return a ordinal value of (@code UserRole) of user object
     * @throws DaoException if a database access error occurs
     */
    int getUserRoleId(String login) throws DaoException;
    /**
     * Gets a row from the table using id,
     * sets path to the user picture to {@code User} object that represents this id.
     *
     * @param userId an id of the user object
     * @param imagePath path to be set to the {@code User} object
     * @return a {@code true} if the table was updated, {@code false} otherwise
     * @throws DaoException if a database access error occurs
     */
    boolean setUserAvatar(Long userId, String imagePath) throws DaoException;
}
