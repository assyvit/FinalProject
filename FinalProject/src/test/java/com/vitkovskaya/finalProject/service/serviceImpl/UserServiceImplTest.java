package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private final static Logger logger = LogManager.getLogger();
    private final static long ID_EXIST = 2L;
    private final static String LOGIN = "cleaner1test@gmail.com";
    private final static String LOGIN_DONT_EXIST = "cleaner1test@gmail789.com";
    UserServiceImpl userService;


    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in userServiceImpl");
        userService = new UserServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in userServiceImpl");
        userService = null;
          }

    @DataProvider(name = "User")
    private static Object[] userProvider() {
        return new Object[]{
                new User(UserRole.CLEANER, 2L, "cleaner1test@gmail.com",
                        "e84ceafb2fadee8f4290972263face2f1d7887b9", true, null)
        };
    }

    @Test
    public void testFindUserByLoginAndPassword() throws ServiceException {
        boolean actual = userService.findUserByLoginAndPassword(LOGIN,
                "2016-Om-7");
        assertTrue(actual);

    }

    @Test
    public void testCheckUserLoginPositive() throws ServiceException {
        boolean actual = userService.checkUserLogin(LOGIN);
        assertTrue(actual);
    }

    @Test
    public void testCheckUserLoginPositiveNegative() throws ServiceException {
        boolean actual = userService.checkUserLogin(LOGIN_DONT_EXIST);
        assertFalse(actual);
    }

    @Test (dataProvider = "User")
    public void testChangePassword(User user) throws ServiceException {
        boolean actual = userService.changePassword(user, "2016-Om-7");
        assertTrue(actual);
    }

    @Test // FIXME: 02.03.2020 
    public void testRegisterUser() throws ServiceException {
    }
    @DataProvider(name = "CleanerRegister")
    private static Object[] []cleanerProvider() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int i = random.nextInt(200) + 100;
        String login = builder.append("cleaner").append(i).append("@gmail.com").toString();
        return new Object[] []{
                {new User(UserRole.CLEANER,login,
                        "e84ceafb2fadee8f4290972263face2f1d7887b9", true, null),
                        new HashMap<String, String>(){{
                            put("login", login);
                            put("password", "2016-Om-7");
                            put("firstName", "Анна");
                            put("lastName", "Баранова");
                            put("address", " Городецкая 36-2");
                            put("telephoneNumber", "375296698696");
                        }}
                }};

    }
    @Test (dataProvider = "CleanerRegister")
    public void testRegisterCleaner(User user, Map<String, String> map) throws ServiceException {
      User actual =  userService.registerCleaner(map).get();
        user.setUserId(actual.getUserId());
      assertEquals(actual, user);
    }
    @DataProvider(name = "ClientRegister")
    private static Object[] []clientProvider() {
        StringBuilder builder = new StringBuilder();
//        int min = 200;
//        int max = 300;
//        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(200) + 100;
        String login = builder.append("client").append(i).append("@gmail.com").toString();
        return new Object[] []{
                {new User(UserRole.CLIENT, login,
                        "e84ceafb2fadee8f4290972263face2f1d7887b9", true, null),
                        new HashMap<String, String>(){{
                            put("login", login);
                            put("password", "2016-Om-7");
                            put("firstName", "Анна");
                            put("lastName", "Баранова");
                            put("address", " Городецкая 36-2");
                            put("telephoneNumber", "375296698696");
                        }}
                }};

    }
    @Test (dataProvider = "ClientRegister")
    public void testRegisterClient(User user, Map<String, String> map) throws ServiceException {
        User actual =  userService.registerClient(map).get();
        user.setUserId(actual.getUserId());
        assertEquals(actual, user);
    }

    @DataProvider(name = "UserRoleProvider")
    private static Object[][] loginProvider() {
        return new Object[][]{
                {3, "client1@gmail.com"},
                {2, "cleaner1test@gmail.com"},
                {1, "admin@gmail.com"}
        };
    }

    @Test(dataProvider = "UserRoleProvider")
    public void testGetUserRoleId(int roleId, String login) throws ServiceException {
        int actual = userService.getUserRoleId(login);
        assertEquals(actual, roleId);
    }

    @Test(dataProvider = "User")
    public void testFindByLogin(User user) throws ServiceException {
        User actual = userService.findByLogin(LOGIN).get();
        assertEquals(actual, user);
    }

    @Test(dataProvider = "User")
    public void testFindById(User user) throws ServiceException {
        User actual = userService.findById(ID_EXIST).get();
        assertEquals(actual, user);
    }

    @Test
    public void testChangeUserStatus() throws ServiceException {
        boolean result = userService.changeUserStatus(4L, false);
        assertTrue(result);
    }

    @Test
    public void testSetUserAvatar() throws ServiceException {
//        String expected = "/uploads/ebbad530-3107-4dc7-a24a-9b28d4a92636.png";
//        userService.setUserAvatar(4L, "/uploads/ebbad530-3107-4dc7-a24a-9b28d4a92636.png");
//        User user = userService.findById(4L).get();
//        String actual = user.getAvatarPath();
//        assertEquals(actual, expected);
    }
}