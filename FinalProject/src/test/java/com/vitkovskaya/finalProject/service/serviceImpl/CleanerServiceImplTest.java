package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.entity.UserRole;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class CleanerServiceImplTest {
    private final static Logger logger = LogManager.getLogger();
    private final static long ID = 2L;
    CleanerServiceImpl cleanerService;
    private static Optional<Cleaner> correct;
    private static Optional<Cleaner> incorrect;

    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in cleanerServiceTest");
        cleanerService = new CleanerServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in cleanerServiceTest");
        cleanerService = null;
    }

    @Test
    public void testFindAll() throws ServiceException {
        assertTrue(!cleanerService.findAll().isEmpty());
    }

    @Test (dataProvider = "Cleaner")
    public void testFindCleanerById( Cleaner cleaner) throws ServiceException {
        Cleaner actual = cleanerService.findCleanerById(66L).get();
        assertEquals(actual, cleaner);
    }
    @Test (dataProvider = "Cleaner")
    public void testFindCleanerByIdNegative( Cleaner cleaner) throws ServiceException {
        Cleaner actual = cleanerService.findCleanerById(77L).get();
        assertNotEquals(actual, cleaner);
    }

    @DataProvider(name = "Cleaner")
    private static Object[] cleanerProvider() {
        return new Object[]{
               new Cleaner(66L, "Ольга", "Акимова", "375258527481",
                       "Широкая 15-85")
        };
    }
    @Test (dataProvider = "Cleaner")
    public void testUpdateCleaner(Cleaner cleaner) throws ServiceException {
        boolean actual = cleanerService.updateCleaner(cleaner);
        assertTrue(actual);

    }

    @Test
    public void testFindBlockedCleaners() throws ServiceException {
        assertNotNull(cleanerService.findBlockedCleaners());
    }
}