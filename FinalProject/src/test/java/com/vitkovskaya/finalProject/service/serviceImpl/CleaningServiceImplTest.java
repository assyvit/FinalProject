package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.entity.*;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.testng.Assert.*;

public class CleaningServiceImplTest {
    private final static Logger logger = LogManager.getLogger();
    private final static long CLEANING_ID = 2L;
    private final static long CLEANING_ID_EMPTY = 2L;
    private final static long CLEANER_ID = 2L;
    CleaningServiceImpl cleaningService;


    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in cleaningServiceTest");
        cleaningService = new CleaningServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in cleaningServiceTest");
        cleaningService = null;
    }

    @DataProvider(name = "CleaningAdd")
    private static Object[][] cleanerProvider() {
        return new Object[][]{
                {new HashMap<String, String>() {{
                    put("name", "Химчистка ковра");
                    put("price", "45.00");
                    put("quantity", "1");
                    put("type", "carpet_cleaning");
                    put("description", "Description");
                }}, 7L
                }};

    }

    @Test (dataProvider = "CleaningAdd")
    public void testAddCleaning(Map<String, String> map, long id) throws ServiceException {
      boolean actual =  cleaningService.addCleaning(map, id);
      assertTrue(actual);
    }

    @Test
    public void testFindAllCleaning() throws ServiceException {
        assertTrue(!cleaningService.findAllCleaning().isEmpty());
    }

    @DataProvider(name = "CleanerId")
    private static Object[] cleanerIdProvider() {
        return new Object[]{
                7L, 8L, 9L, 10L
        };
    }

    @Test(dataProvider = "CleanerId")
    public void testFindCleaningByCleanerId(long id) throws ServiceException {
        assertTrue(!cleaningService.findCleaningByCleanerId(id).isEmpty());
    }

    @DataProvider(name = "Cleaning")
    private static Object[] cleaningProvider() {
        return new Object[]{
                new Cleaning(1L, "Уборка  комнаты", new BigDecimal(45.00).setScale(2),
                        CleaningType.CLEANING,
                        "Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли",
                        1, true, 7L)
        };
    }

    @Test(dataProvider = "Cleaning")
    public void testFindCleaningById(Cleaning cleaning) throws ServiceException {
        Cleaning actual = cleaningService.findCleaningById(1L).get();
        assertEquals(actual, cleaning);
    }

    @Test
    public void testChangeCleaningStatus() throws ServiceException {
        boolean actual = cleaningService.changeCleaningStatus(1L, true);
        assertTrue(actual);
    }

    @Test(dataProvider = "Cleaning")
    public void testUpdateCleaning(Cleaning cleaning) throws ServiceException {
        boolean actual = cleaningService.updateCleaning(cleaning);
        assertTrue(actual);
    }

    @Test // FIXME: 02.03.2020
    public void testGetCleaningPrice() throws ServiceException {
    }
}