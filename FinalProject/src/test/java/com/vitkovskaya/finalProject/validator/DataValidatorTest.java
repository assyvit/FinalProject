package com.vitkovskaya.finalProject.validator;

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
import java.util.Random;

import static org.testng.Assert.*;

public class DataValidatorTest {
    private final static Logger logger = LogManager.getLogger();
    DataValidator validator = new DataValidator();

    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in cleanerServiceTest");
        validator = new DataValidator();
    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in cleanerServiceTest");
        validator = null;
    }

    @DataProvider(name = "loginProvider")
    private static Object[] loginProvider() {
        return new Object[]{
                "test@gmail.com",
                "test@mail.ru",
                "test1@mail.ru",
                "test_1@tut.by"
        };
    }

    @Test(dataProvider = "loginProvider")
    public void testValidateLoginPositive(String login) {
        assertTrue(validator.validateLogin(login));
    }

    @DataProvider(name = "loginIncorrectProvider")
    private static Object[] loginIncorrectProvider() {
        return new Object[]{
                "testgmail.com",
                "test@mailru",
                "test1mailru",
                "Loginloginloginloginloginloginlogin45@gmail.com",
                "loginlogin@gmail,com",
                "",
                null
        };
    }

    @Test(dataProvider = "loginIncorrectProvider")
    public void testValidateLoginNegative(String login) {
        assertFalse(validator.validateLogin(login));
    }

    @DataProvider(name = "passwordProvider")
    private static Object[] passwordProvider() {
        return new Object[]{
                "2016-Om-7",
                "qqQQ11-ppp",
                "Qaz123456_",
        };
    }

    @Test(dataProvider = "passwordProvider")
    public void testValidatePassword(String password) {
        assertTrue(validator.validatePassword(password));
    }

    @DataProvider(name = "passwordIncorrectProvider")
    private static Object[] passwordIncorrectProvider() {
        return new Object[]{
                "201-Om7",
                "qqqqqqq11-ppp",
                "QQQQQQQQQQQQ",
                "",
                "qqqqqpoiu",
                "123456789",
                "12345QWq",
                null
        };
    }

    @Test(dataProvider = "passwordIncorrectProvider")
    public void testValidatePasswordNegative(String password) {
        assertFalse(validator.validatePassword(password));
    }

    @Test
    public void testDoublePasswordCheck() {
        String password = "2016-Om-7";
        String confirm = "2016-Om-7";
        assertTrue(validator.doublePasswordCheck(password, confirm));
    }

    @DataProvider(name = "stringProvider")
    private static Object[] stringProvider() {
        return new Object[]{
                "201-Om7",
                "qqqqqqq11-ppp",
                "QQQQQQQQQQQQ",
                "1",
                "qqqqqpoiu",
                "123456789",
                "12345QWq"
        };
    }

    @Test(dataProvider = "stringProvider")
    public void testIsValidStringPositive(String example) {
        assertTrue(validator.isValidString(example));
    }

    @DataProvider(name = "stringIncorrectProvider")
    private static Object[] stringIncorrectProvider() {
        return new Object[]{
                "",
                "The Extension classloader has been renamed to Platform class loader. All classes in the Java" +
                        " SE Platform are guaranteed to be visible through the platform class loader. In addition," +
                        " the classes in modules that are standardized under the Java Community Process" +
                        " but not part of the Java SE Platform are guaranteed to be visible through the" +
                        " platform class loader. The Platform class loader is not an instance of URLClassLoader, " +
                        "but an internal class.\n" +
                        "The System(Application) classloader is no longer an instance of URLClassLoader," +
                        " but an internal class. It loads the classes in modules that are neither Java SE nor " +
                        "JDK modules.",
                null
        };
    }

    @Test(dataProvider = "stringIncorrectProvider")
    public void testIsValidStringNegative(String example) {
        assertFalse(validator.isValidString(example));
    }

    @Test(dataProvider = "stringProvider")
    public void testIsValidTextPositive(String example) {
        assertTrue(validator.isValidText(example));
    }

    @Test(dataProvider = "stringIncorrectProvider")
    public void testIsValidTextNegative(String example) {
        assertFalse(validator.isValidText(example));
    }

    @DataProvider(name = "CleaningCorrect")
    private static Object[] cleaningCorrectProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("name", "Химчистка ковра");
                    put("price", "45.00");
                    put("quantity", "1");
                    put("type", "carpet_cleaning");
                    put("description", "Description");
                }},
        };
    }

    @Test(dataProvider = "CleaningCorrect")
    public void testValidateCleaningInputData(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateCleaningInputData(map);
        assertFalse(actual.containsValue(""));
    }

    @DataProvider(name = "CleaningIncorrect")
    private static Object[] cleaningIncorrectProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("name", null);
                    put("price", "15.00");
                    put("quantity", "");
                    put("type", "carpet_cleaning");
                    put("description", "Description");
                }}
        };
    }

    @Test(dataProvider = "CleaningIncorrect")
    public void testValidateCleaningInputDataIncorrect(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateCleaningInputData(map);
        assertTrue(actual.containsValue(""));
    }

    @DataProvider(name = "loginPasswordProvider")
    private static Object[][] loginPasswordProvider() {
        return new Object[][]{
                {"cleaner@gmail.com", "2016-Om-7", "2016-Om-7"},
                {"cleaner@mail.ru", "qwerTy_45", "qwerTy_45"},
                {"cleaner1@yahoo.com", "Qaz123456_", "Qaz123456_"},
        };
    }

    @Test(dataProvider = "loginPasswordProvider")
    public void testValidateLoginPassword(String login, String password, String confirm) {
        boolean actual = validator.validateLoginPassword(login, password, confirm);
        assertTrue(actual);
    }

    @DataProvider(name = "loginPasswordWrongProvider")
    private static Object[][] loginPasswordWrongProvider() {
        return new Object[][]{
                {"cleaner@gmailcom", "2016-Om-7", "2016-Om-7"},
                {"cleaner@mail.ru", "qwerTy_45", ""},
                {"cleaner1@yahoo.com", "Qaz6_", "Qaz6_"},
                {"cleaner @gmail .com", "2016-Om-7", "2016-Om-7"},
                {"cleaner@mail.ru", "qwerTy_45", null},
        };
    }

    @Test(dataProvider = "loginPasswordWrongProvider")
    public void testValidateLoginPasswordNegative(String login, String password, String confirm) {
        boolean actual = validator.validateLoginPassword(login, password, confirm);
        assertFalse(actual);
    }

    @DataProvider(name = "passwordChangeProvider")
    private static Object[][] passwordChangeProvider() {
        return new Object[][]{
                {"2016-Om-7", "2016-Om-7"},
                {"qwerTy_45", "qwerTy_45"},
                {"Qaz123456_", "Qaz123456_"},
        };
    }

    @Test(dataProvider = "passwordChangeProvider")
    public void testValidateChangedPassword(String password, String confirm) {
        boolean actual = validator.validateChangedPassword(password, confirm);
        assertTrue(actual);
    }

    @DataProvider(name = "passwordChangeWrongProvider")
    private static Object[][] passwordChangeWrongProvider() {
        return new Object[][]{
                {"20Om-7", "20Om-7"},
                {"qwerTy_745", "qwerTy_45"},
                {"Qaz123456_", "Qaz23456_"},
        };
    }

    @Test(dataProvider = "passwordChangeWrongProvider")
    public void testValidateChangedPasswordNegative(String password, String confirm) {
        boolean actual = validator.validateChangedPassword(password, confirm);
        assertFalse(actual);
    }

    @DataProvider(name = "CleanerRegister")
    private static Object[] cleanerProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("login", "cleaner28@gmail.com");
                    put("password", "2016-Om-7");
                    put("passwordConfirmation", "2016-Om-7");
                    put("firstName", "Анна");
                    put("lastName", "Баранова");
                    put("address", " Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }},
                new HashMap<String, String>() {{
                    put("login", "cleaner28@gmail.com");
                    put("password", "qwer78Q_96");
                    put("passwordConfirmation", "qwer78Q_96");
                    put("firstName", "Анна");
                    put("lastName", "Баранова");
                    put("address", "Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }}
        };
    }

    @Test(dataProvider = "CleanerRegister")
    public void testValidateUserInputDate(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateUserInputDate(map);
        assertFalse(actual.containsValue(""));
    }

    @DataProvider(name = "CleanerRegisterWrong")
    private static Object[] cleanerWrongProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("login", "cleaner28@gmail.com");
                    put("password", "2016-Om-7");
                    put("passwordConfirmation", null);
                    put("firstName", "Анна");
                    put("lastName", "Баранова");
                    put("address", " Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }},
                new HashMap<String, String>() {{
                    put("login", "cleaner28@gmail.com");
                    put("password", "qwer78Q_96");
                    put("passwordConfirmation", "qwer78Q_96");
                    put("firstName", "Анна");
                    put("lastName", "");
                    put("address", "Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }},
                new HashMap<String, String>() {{
                    put("login", "cleaner28@gmail.com");
                    put("password", "qwer78Q_96");
                    put("passwordConfirmation", "qwer78Q_96");
                    put("firstName", "Анна");
                    put("lastName", "fdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
                    put("address", "Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }}
        };
    }

    @Test(dataProvider = "CleanerRegisterWrong")
    public void testValidateUserInputDateWrong(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateUserInputDate(map);
        assertTrue(actual.containsValue(""));
    }

    @DataProvider(name = "CleanerUpdate")
    private static Object[] cleanerUpdateProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("firstName", "Анна");
                    put("lastName", "Баранова");
                    put("address", " Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }}
        };
    }

    @Test(dataProvider = "CleanerUpdate")
    public void testValidateUserUpdateDate(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateUserUpdateDate(map);
        assertFalse(actual.containsValue(""));
    }

    @DataProvider(name = "CleanerUpdateWrong")
    private static Object[] cleanerWrongUpdate() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("firstName", null);
                    put("lastName", "Баранова");
                    put("address", " Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }},
                new HashMap<String, String>() {{
                    put("firstName", "Анна");
                    put("lastName", "");
                    put("address", "Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }},
                new HashMap<String, String>() {{
                    put("firstName", "Анна");
                    put("lastName", "fdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
                    put("address", "Городецкая 36-2");
                    put("telephoneNumber", "375296698696");
                }}
        };
    }

    @Test(dataProvider = "CleanerUpdateWrong")
    public void testValidateUserUpdateDateWrong(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateUserUpdateDate(map);
        assertTrue(actual.containsValue(""));
    }

    @DataProvider(name = "Order")
    private static Object[] orderProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("paymentType", "cash");
                    put("executing_date", "2020-03-15 15:00");
                    put("comment", "Comment to the order");
                }},
                new HashMap<String, String>() {{
                    put("paymentType", "card");
                    put("executing_date", "2020-04-16 12:45");
                    put("comment", "Comment to the order fffffff");
                }}
        };
    }

    @Test(dataProvider = "Order")
    public void testValidateOrderInputData(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateOrderInputData(map);
        assertFalse(actual.containsValue(""));
    }

    @DataProvider(name = "OrderWrong")
    private static Object[] orderWrongProvider() {
        return new Object[]{
                new HashMap<String, String>() {{
                    put("paymentType", "cash");
                    put("executing_date", "2020-03-15 15:00");
                    put("comment", null);
                }},
                new HashMap<String, String>() {{
                    put("paymentType", "");
                    put("executing_date", "2020-03-15 15-00");
                    put("comment", "Comment");
                }},
                new HashMap<String, String>() {{
                    put("paymentType", "card");
                    put("executing_date", "2020-04-16 12:45");
                    put("comment", "The Extension classloader has been renamed to Platform class loader. All classes in the Java\" +\n" +
                            " SE Platform are guaranteed to be visible through the platform class loader. In addition,\" +\n" +
                            " the classes in modules that are standardized under the Java Community Process\" +\n" +
                            " but not part of the Java SE Platform are guaranteed to be visible through the\" +\n" +
                            " platform class loader. The Platform class loader is not an instance of URLClassLoader, \" +\n" +
                            " but an internal class.\n" +
                            " The System(Application) classloader is no longer an instance of URLClassLoader,\" +\n" +
                            " but an internal class. It loads the classes in modules that are neither Java SE nor \" +\n" +
                            " JDK modules.\",");
                }}
        };
    }

    @Test(dataProvider = "OrderWrong")
    public void testValidateOrderInputDataWrong(HashMap<String, String> map) {
        Map<String, String> actual = validator.validateOrderInputData(map);
        assertTrue(actual.containsValue(""));
    }
}