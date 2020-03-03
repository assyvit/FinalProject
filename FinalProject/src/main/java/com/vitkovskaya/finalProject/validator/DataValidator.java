package com.vitkovskaya.finalProject.validator;

import com.vitkovskaya.finalProject.command.ConstantName;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code DataValidator} class contains methods for
 * validating an input data (data from forms).
 */
public class DataValidator {
    private final static Logger logger = LogManager.getLogger();
    /**
     * The {@code String} value that is pattern for login.
     */
    private final static String REGEX_CHECK_FOR_LOGIN_AS_EMAIL = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$"; //p{lower} не совсем корректно
    /**
     * The {@code String} value that is pattern for password.
     */

    private final static String REGEX_CHECK_FOR_PASSWORD = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[#$%!\\-]).{8,40})";
    /**
     * The {@code String} value that is pattern for date format YYYY-MM-DD.
     */
    private final static String REGEX_FOR_DATE = "^(20[0-4][0-9]|2050)[-/](0[1-9]|1[0-2])[-/](0[1-9]|[12][0-9]|3[01])";
    /**
     * The {@code String} value that is pattern for time format HH:MM (24 hours format).
     */
    private final static String REGEX_FOR_TIME = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    private final static String REGEX_FOR_DECIMAL_VALUE = "\\d{0,10}(\\.\\d{1,2})?";
    private final static String REGEX_FOR_INTEGER_VALUE = "\\d{1,3}";
    private final static int MIN_STRING_LENGTH = 0;
    private final static int MAX_STRING_LENGTH = 45;
    private final static int MAX_TEXT_LENGTH = 500;

    /**
     * Validates input login, if it matches pattern.
     *
     * @param login password input value.
     * @return {@code true} if password matches pattern,
     * otherwise {@code false}
     */
    public boolean validateLogin(String login) {
        return Pattern.matches(REGEX_CHECK_FOR_LOGIN_AS_EMAIL, login) &&
                isValidString(login);
    }

    /**
     * Validates input password, if it matches pattern.
     *
     * @param password password input value.
     * @return {@code true} if password matches pattern,
     * otherwise {@code false}
     */
    public boolean validatePassword(String password) {
        boolean a = Pattern.matches(REGEX_CHECK_FOR_PASSWORD, password);
        boolean c = password.matches(REGEX_CHECK_FOR_PASSWORD);
        boolean b = isValidString(password);
        return a && b;

    }

    /**
     * Validates input password and password confirmation, if they are equal.
     *
     * @param passwordSampleOne password input value.
     * @param passwordSampleTwo password confirmation input value.
     * @return {@code true} if password are equal,
     * otherwise {@code false}
     */
    public boolean doublePasswordCheck(String passwordSampleOne, String passwordSampleTwo) {
        return passwordSampleOne.equals(passwordSampleTwo);
    }

    /**
     * Validates input string, if it matches conditions.
     *
     * @param parameter string input value.
     * @return {@code true} if string is not null, not empty and string length is 45 symbols or less,
     * otherwise {@code false}
     */
    public boolean isValidString(String parameter) {
        return parameter != null && parameter.length() > MIN_STRING_LENGTH
                && parameter.length() <= MAX_STRING_LENGTH;
    }

    /**
     * Validates input strings, if it matches conditions.
     *
     * @param text string input value.
     * @return {@code true} if string is not null, not empty and string length is 500 symbols or less,
     * otherwise {@code false}
     */
    public boolean isValidText(String text) {
        return text != null && text.length() > MIN_STRING_LENGTH &&
                text.length() <= MAX_TEXT_LENGTH;
    }

    public String takeDate(String inputDate) {
        String executeDate = null;
        Pattern datePattern = Pattern.compile(REGEX_FOR_DATE);
        Matcher matcherDate = datePattern.matcher(inputDate);
        while (matcherDate.find()) {
            executeDate = matcherDate.group();
        }
        return executeDate;
    }

    public String takeTime(String inputDate) {
        String executeTime = null;
        Pattern datePattern = Pattern.compile(REGEX_FOR_TIME);
        Matcher matcherTime = datePattern.matcher(inputDate);
        while (matcherTime.find()) {
            executeTime = matcherTime.group();
        }
        return executeTime;
    }

    public Map<String, String> validateCleaningInputData(Map<String, String> inputData) {
        if (!isValidString(inputData.get(ConstantName.PARAMETER_CLEANING_NAME))) {
            inputData.put(ConstantName.ATTRIBUTE_CLEANING_NAME_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
            inputData.put(ConstantName.PARAMETER_CLEANING_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_CLEANING_PRICE)) &&
                !inputData.get(ConstantName.PARAMETER_CLEANING_PRICE).matches(REGEX_FOR_DECIMAL_VALUE)) {
            inputData.put(ConstantName.ATTRIBUTE_CLEANING_PRICE_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_PRICE));
            inputData.put(ConstantName.PARAMETER_CLEANING_PRICE, ConstantName.ATTRIBUTE_EMPTY_VALUE);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_CLEANING_QUANTITY)) &&
                !inputData.get(ConstantName.PARAMETER_CLEANING_QUANTITY).matches(REGEX_FOR_INTEGER_VALUE)) {
            inputData.put(ConstantName.ATTRIBUTE_CLEANING_QUANTITY_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_CLEANING_QUANTITY));
            inputData.put(ConstantName.PARAMETER_CLEANING_QUANTITY, ConstantName.ATTRIBUTE_EMPTY_VALUE);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_CLEANING_TYPE))) {
            inputData.put(ConstantName.ATTR_CLEANING_TYPE_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
            inputData.put(ConstantName.PARAMETER_CLEANING_TYPE, ConstantName.ATTRIBUTE_EMPTY_VALUE);
        }
        if (!isValidText(inputData.get(ConstantName.PARAMETER_CLEANING_DESCRIPTION))) {
            inputData.put(ConstantName.ATTR_CLEANING_DESCRIPTION_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_TEXT));
            inputData.put(ConstantName.PARAMETER_CLEANING_DESCRIPTION, ConstantName.ATTRIBUTE_EMPTY_VALUE);
        }
        return inputData;
    }

    /**
     * Validates input data for login form - login, password. Checks if the params match patterns,
     * password and password confirmation are equal
     *
     * @param login                login input value.
     * @param password             password input value.
     * @param passwordConfirmation password confirmation input value.
     * @return {@code true} if params match patterns, not null, otherwise {@code false}
     */
    public boolean validateLoginPassword(String login, String password, String passwordConfirmation) {
        return validateLogin(login) && (validatePassword(password) &&
                doublePasswordCheck(password, passwordConfirmation));
    }

    /**
     * Validates input data for change password form - new password
     * and password confirmation. Checks if the params match patterns,
     * are not null and are 45 symbols or less
     *
     * @param newPassword          password input value.
     * @param passwordConfirmation password confirmation input value.
     * @return {@code true} if params match patterns, not null, otherwise {@code false}
     */
    public boolean validateChangedPassword(String newPassword, String passwordConfirmation) {
        return doublePasswordCheck(newPassword, passwordConfirmation) &&
                validatePassword(newPassword) &&
                validatePassword(passwordConfirmation);
    }

    public Map<String, String> validateUserInputDate(Map<String, String> inputData) {

        if (!validateLogin(inputData.get(ConstantName.PARAMETER_LOGIN))) {
            inputData.put(ConstantName.PARAMETER_LOGIN, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_LOGIN_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_LOGIN));
        }
        if (!doublePasswordCheck(inputData.get(ConstantName.PARAMETER_PASSWORD),
                inputData.get(ConstantName.PARAMETER_PASSWORD_CONFIRMATION))) {
            inputData.put(ConstantName.PARAMETER_PASSWORD, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.PARAMETER_PASSWORD_CONFIRMATION, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_PASSWORD_MATCH,
                    MessageManager.getProperty(ConstantName.MESSAGE_PASSWORDS_NOT_EQUAL));
        }
        if (!validatePassword(inputData.get(ConstantName.PARAMETER_PASSWORD))) {
            inputData.put(ConstantName.PARAMETER_PASSWORD, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.PARAMETER_PASSWORD_CONFIRMATION, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_PASSWORD_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_PASSWORD));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_FIRST_NAME))) {
            inputData.put(ConstantName.PARAMETER_FIRST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_FIRST_NAME_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_LAST_NAME))) {
            inputData.put(ConstantName.PARAMETER_LAST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_LAST_NAME_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_ADDRESS))) {
            inputData.put(ConstantName.PARAMETER_ADDRESS, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_ADDRESS_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_TELEPHONE_NUMBER))) {
            inputData.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_TELEPHONE_NUMBER_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        return inputData;
    }

    public Map<String, String> validateUserUpdateDate(Map<String, String> inputData) {
        logger.log(Level.DEBUG,  "  IN validateUserInputDate");
        if (!isValidString(inputData.get(ConstantName.PARAMETER_FIRST_NAME))) {
            inputData.put(ConstantName.PARAMETER_FIRST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_FIRST_NAME_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_LAST_NAME))) {
            inputData.put(ConstantName.PARAMETER_LAST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_LAST_NAME_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_ADDRESS))) {
            inputData.put(ConstantName.PARAMETER_ADDRESS, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_ADDRESS_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_TELEPHONE_NUMBER))) {
            inputData.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_TELEPHONE_NUMBER_INCORRECT,
                    MessageManager.getProperty(ConstantName.MESSAGE_INCORRECT_STRING));
        }
        return inputData;
    }

    // FIXME: 28.02.2020
    public Map<String, String> validateUserEditDate(Map<String, String> inputData) {
        if (!isValidString(inputData.get(ConstantName.PARAMETER_FIRST_NAME))) {
            inputData.put(ConstantName.PARAMETER_FIRST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_FIRST_NAME_INCORRECT, ConstantName.MESSAGE_INCORRECT_STRING);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_LAST_NAME))) {
            inputData.put(ConstantName.PARAMETER_LAST_NAME, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_LAST_NAME_INCORRECT, ConstantName.MESSAGE_INCORRECT_STRING);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_ADDRESS))) {
            inputData.put(ConstantName.PARAMETER_ADDRESS, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_ADDRESS_INCORRECT, ConstantName.MESSAGE_INCORRECT_STRING);
        }
        if (!isValidString(inputData.get(ConstantName.PARAMETER_TELEPHONE_NUMBER))) {
            inputData.put(ConstantName.PARAMETER_TELEPHONE_NUMBER, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_TELEPHONE_NUMBER_INCORRECT, ConstantName.MESSAGE_INCORRECT_STRING);
        }
        return inputData;
    }

    public Map<String, String> validateOrderInputData(Map<String, String> inputData) {
        if (!isValidString(inputData.get(ConstantName.PARAMETER_PAYMENT_TYPE))) {
            inputData.put(ConstantName.PARAMETER_PAYMENT_TYPE, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_PAYMENT_TYPE_ERROR, ConstantName.MESSAGE_INCORRECT_PAYMENT_TYPE);
        }
        if (!isValidString(inputData.get(ConstantName.EXECUTING_DATE)) &&
                !validateDateTime(inputData.get(ConstantName.EXECUTING_DATE))) {
            logger.log(Level.DEBUG, validateDateTime(inputData.get(ConstantName.EXECUTING_DATE)));
            inputData.put(ConstantName.EXECUTING_DATE, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_DATE_ERROR, ConstantName.MESSAGE_DATE_ERROR);
        }
        if (inputData.get(ConstantName.PARAMETER_ORDER_COMMENT) != null &&
                inputData.get(ConstantName.PARAMETER_ORDER_COMMENT).length() > 500) {
            inputData.put(ConstantName.PARAMETER_ORDER_COMMENT, ConstantName.ATTRIBUTE_EMPTY_VALUE);
            inputData.put(ConstantName.ATTRIBUTE_TEXT_ERROR, ConstantName.MESSAGE_TEXT_ERROR);
        }
        return inputData;
    }

    private boolean validateDateTime(String date) {
        return takeDate(date) != null && takeTime(date) != null;
    }
}
