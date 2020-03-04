package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.EntityTransaction;
import com.vitkovskaya.finalProject.dao.impl.CleanerDaoImpl;
import com.vitkovskaya.finalProject.dao.impl.ClientDaoImpl;
import com.vitkovskaya.finalProject.dao.impl.UserDaoImpl;
import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.service.UserService;
import com.vitkovskaya.finalProject.util.PasswordHashGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public boolean findUserByLoginAndPassword(String login, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        PasswordHashGenerator hashGenerator = new PasswordHashGenerator();
        boolean flag;
        String hashPassword = hashGenerator.hash(password);
        transaction.beginNoTransaction(userDao);
        try {
            flag = userDao.findUserByLoginAndPassword(login, hashPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return flag;
    }
    @Override
    public boolean checkUserLogin(String login) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        boolean exists;
        transaction.beginNoTransaction(userDao);
        try {
            exists = userDao.checkUserLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return exists;
    }
    @Override
    public boolean changePassword(User user, String newPassword) throws ServiceException {
        boolean updated;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        PasswordHashGenerator hashGenerator = new PasswordHashGenerator();
        transaction.beginNoTransaction(userDao);
        user.setPassword(hashGenerator.hash(newPassword));
        try {
            updated = userDao.update(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while saving changes", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }
    @Override
    public long registerUser(String login, String password, UserRole userRole) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        PasswordHashGenerator hashGenerator = new PasswordHashGenerator();
        long userId;
        transaction.beginNoTransaction(userDao);
        User user = new User();
        user.setLogin(login);
        user.setPassword(hashGenerator.hash(password));
        user.setUserRole(userRole);
        user.setIsActive(true);
        try {
            userDao.create(user);
            userId = userDao.getUserId(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return userId;
    }
    @Override
    public Optional<User> registerCleaner(Map<String, String> parameters) throws ServiceException {
        Optional<User> optionalUser;
        User user = new User();
        Cleaner cleaner = new Cleaner();
        UserDaoImpl userDao = new UserDaoImpl();
        CleanerDaoImpl cleanerDao = new CleanerDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        PasswordHashGenerator hashGenerator = new PasswordHashGenerator();
        long userId;
        user.setLogin(parameters.get(ConstantName.PARAMETER_LOGIN));
        user.setPassword(hashGenerator.hash(parameters.get(ConstantName.PARAMETER_PASSWORD)));
        user.setUserRole(UserRole.CLEANER);
        user.setIsActive(true);
        transaction.begin(cleanerDao, userDao);
        try {
            userDao.create(user);
            userId = userDao.getUserId(user.getLogin());
            user.setUserId(userId);
            optionalUser = Optional.of(user);
            cleaner.setCleanerId(userId);
            cleaner.setFirstName(parameters.get(ConstantName.PARAMETER_FIRST_NAME));
            cleaner.setLastName(parameters.get(ConstantName.PARAMETER_LAST_NAME));
            cleaner.setAddress(parameters.get(ConstantName.PARAMETER_ADDRESS));
            cleaner.setTelephoneNumber(parameters.get(ConstantName.PARAMETER_TELEPHONE_NUMBER));
            cleanerDao.create(cleaner);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return optionalUser;
    }
    @Override
    public Optional<User> registerClient(Map<String, String> parameters) throws ServiceException {
        Optional<User> optionalUser;
        EntityTransaction transaction = new EntityTransaction();
        UserDaoImpl userDao = new UserDaoImpl();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        PasswordHashGenerator hashGenerator = new PasswordHashGenerator();
        long userId;
        User user = new User();
        Client client = new Client();
        user.setLogin(parameters.get(ConstantName.PARAMETER_LOGIN));
        user.setPassword(hashGenerator.hash(parameters.get(ConstantName.PARAMETER_PASSWORD)));
        user.setUserRole(UserRole.CLIENT);
        user.setIsActive(true);
        transaction.begin(clientDao, userDao);
        try {
            userDao.create(user);
            userId = userDao.getUserId(user.getLogin());
            user.setUserId(userId);
            optionalUser = Optional.of(user);
            client.setId(userId);
            client.setFirstName(parameters.get(ConstantName.PARAMETER_FIRST_NAME));
            client.setLastName(parameters.get(ConstantName.PARAMETER_LAST_NAME));
            client.setAddress(parameters.get(ConstantName.PARAMETER_ADDRESS));
            client.setTelephoneNumber(parameters.get(ConstantName.PARAMETER_ADDRESS));
            clientDao.create(client);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return optionalUser;
    }
    @Override
    public int getUserRoleId(String login) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        int roleId;
        transaction.beginNoTransaction(userDao);
        try {
            roleId = userDao.getUserRoleId(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return roleId;
    }
    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        Optional<User> userOptional;
        transaction.beginNoTransaction(userDao);
        try {
            userOptional = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return userOptional;
    }
    @Override
    public Optional<User> findById(Long userId) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        Optional<User> userOptional;
        transaction.beginNoTransaction(userDao);
        try {
            userOptional = userDao.findById(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return userOptional;
    }
    @Override
    public boolean changeUserStatus(long userId, boolean status) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        boolean blocked;
        transaction.beginNoTransaction(userDao);
        try {
            blocked = userDao.changeActiveStatus(userId, status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return blocked;
    }
      @Override
    public boolean setUserAvatar(Long userId, InputStream image) throws ServiceException {
        boolean updated;
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginNoTransaction(userDao);
        try {
            updated = userDao.setUserAvatar(userId, image);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while saving changes", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }
}
