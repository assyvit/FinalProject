package com.vitkovskaya.finalProject.util;

import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.validator.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTimeParser {
    private final static Logger logger = LogManager.getLogger();
    /**
     * The {@code String} value that is pattern for date format YYYY-MM-DD.
     */
    private final static String REGEX_FOR_DATE = "^(20[0-4][0-9]|2050)[-/](0[1-9]|1[0-2])[-/](0[1-9]|[12][0-9]|3[01])";
    /**
     * The {@code String} value that is pattern for time format HH:MM (24 hours format).
     */
    private final static String REGEX_FOR_TIME = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    private final static String DEFAULT_SECONDS_VALUE = ":00";
    private String takeDate(String inputDate) {
        String executeDate = null;
        Pattern datePattern = Pattern.compile(REGEX_FOR_DATE);
        Matcher matcherDate = datePattern.matcher(inputDate);
        while (matcherDate.find()) {
            executeDate = matcherDate.group();
        }
        return executeDate;
    }
    private String takeTime(String inputDate) {
        String executeTime = null;
        Pattern datePattern = Pattern.compile(REGEX_FOR_TIME);
        Matcher matcherTime = datePattern.matcher(inputDate);
        while (matcherTime.find()) {
            executeTime = matcherTime.group();
        }
        return executeTime;
    }
    public LocalDateTime getTime(String dataTime) {
        StringBuilder builder = new StringBuilder();
        String date = takeDate(dataTime.trim());
        String time = builder.append(takeTime(dataTime.trim())).append(DEFAULT_SECONDS_VALUE).toString();
        LocalDateTime executeDateTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
        return executeDateTime;
    }
}
