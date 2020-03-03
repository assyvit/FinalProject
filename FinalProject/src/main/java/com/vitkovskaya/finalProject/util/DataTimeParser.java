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

public class DataTimeParser {
    private final static Logger logger = LogManager.getLogger();
    private final static String DEFAULT_SECONDS_VALUE = ":00";
    public LocalDateTime getTime(String dataTime) {

//        logger.log(Level.DEBUG, dataTime);
        StringBuilder builder = new StringBuilder();
        DataValidator validator = new DataValidator();
        String date = validator.takeDate(dataTime.trim());
//        logger.log(Level.DEBUG, date);
        String time = builder.append(validator.takeTime(dataTime.trim())).append(DEFAULT_SECONDS_VALUE).toString();
//        logger.log(Level.DEBUG, time);
        LocalDateTime executeDateTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
   //     Timestamp timeStamp = Timestamp.valueOf(executeDateTime);
        //  long timeStamp = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        logger.log(Level.DEBUG, timeStamp);
//        logger.log(Level.DEBUG, new Date(timeStamp.getTime()));
        return executeDateTime;
    }
}
