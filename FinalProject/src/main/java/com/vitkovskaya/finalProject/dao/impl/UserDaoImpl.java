package com.vitkovskaya.finalProject.dao.impl;

import com.vitkovskaya.finalProject.dao.AbstractDao;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.TableColumn;
import com.vitkovskaya.finalProject.dao.UserDao;
import com.vitkovskaya.finalProject.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserDaoImpl} class
 * provides access to the tables 'users', 'roles' in the database
 */
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
    private final static Logger logger = LogManager.getLogger();
    private final static String SQL_CHECK_LOGIN_PASSWORD = "SELECT users_id, login, password, fk_user_role, active_status, " +
            " avatar_path  FROM users WHERE login= ? AND password= ?";
    private final static String SQL_SELECT_ALL_USERS = "SELECT users_id, login, password, fk_user_role, active_status, " +
            " avatar_path FROM users ";
    private final static String SQL_SELECT_USER_BY_ID = "SELECT users_id, login, password, fk_user_role, active_status, " +
            " avatar_path FROM users WHERE users_id= ?";
    private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT users_id, login, password, fk_user_role, active_status, " +
            " avatar_path FROM users WHERE login= ?";
    private final static String SQL_CREATE_USER = "INSERT INTO users (login, password, fk_user_role, active_status, avatar_path) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_USER_PASSWORD = "UPDATE users SET password= ? WHERE login= ?";
    private final static String SQL_UPDATE_USER_ACTIVE_STATUS = "UPDATE users SET active_status= ? WHERE users_id= ?";
    private final static String SQL_UPDATE_USER_AVATAR = "UPDATE users SET avatar_path= ? WHERE users_id= ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        User user;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        Optional<User> userOptional;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
            userOptional = Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return userOptional;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean created = false;
        PreparedStatement statement = null;
        ResultSet generatedKeys;
        try {
            statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getUserRole().ordinal());
            statement.setBoolean(4, user.getIsActive());
            statement.setString(5, user.getAvatarPath());
            statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserId(generatedKeys.getInt(1));
                created = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "User creation failed! ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return created;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean updated;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getLogin());
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "User password updating failed!: ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }
    @Override
    public boolean findUserByLoginAndPassword(String login, String password) throws DaoException {
        boolean verified = false;
        logger.log(Level.DEBUG, login + " " + password);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            verified = resultSet.next();
            logger.log(Level.DEBUG, verified + " FROM logInUser USERDAO ");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return verified;
    }
    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        Optional<User> optionalUser;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return optionalUser;
    }
    @Override
    public boolean checkUserLogin(String login) throws DaoException {
        boolean exist;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            exist = resultSet.next();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return exist;
    }
    @Override
    public long getUserId(String login) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return user.getUserId();
    }

    @Override
    public boolean changeActiveStatus(long id, boolean active) throws DaoException {
        boolean updated = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_ACTIVE_STATUS);
            statement.setBoolean(1, active);
            statement.setLong(2, id);
            int result = statement.executeUpdate();
            if (result != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception user status update failed!: ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    @Override
    public int getUserRoleId(String login) throws DaoException {
        int roleId = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roleId = resultSet.getInt(TableColumn.USER_ROLE);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQL exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return roleId;
    }

    @Override
    public boolean setUserAvatar(Long userId, String imagePath) throws DaoException {
        boolean updated;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_AVATAR);
            statement.setString(1, imagePath);
            statement.setLong(2, userId);
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "User avatar updating failed!: ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }
    public boolean setUserAvatar(Long userId, InputStream image) throws DaoException {
        boolean updated;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_AVATAR);
            statement.setBlob(1, image);
            statement.setLong(2, userId);
            updated = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "User avatar updating failed!: ", e);
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return updated;
    }

    /**
     * Creates a new {@code User} object and
     * sets its values using {@code ResultSet}
     *
     * @param resultSet a {@code ResultSet} to build an object
     * @return a {@code User}
     */
    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong(TableColumn.USER_ID));
        user.setLogin(resultSet.getString(TableColumn.USER_LOGIN));
        user.setPassword(resultSet.getString(TableColumn.USER_PASSWORD));
        int roleCode = resultSet.getInt(TableColumn.USER_ROLE);
        switch (roleCode) {
            case 1:
                user.setUserRole(UserRole.ADMIN);
                break;
            case 2:
                user.setUserRole(UserRole.CLEANER);
                break;
            case 3:
                user.setUserRole(UserRole.CLIENT);
                break;
            default:
                logger.log(Level.ERROR, "Unknown role of user");
                throw new IllegalArgumentException();
        }
        user.setIsActive(resultSet.getBoolean(TableColumn.USER_STATUS));
        if (resultSet.getBytes(TableColumn.USER_AVATAR) != null) {
            byte[] imageData = resultSet.getBytes(TableColumn.USER_AVATAR);
            String encode = Base64.getEncoder().encodeToString(imageData);
            user.setAvatarPath(encode);
        }
        return user;
    }
}



