package com.vitkovskaya.finalProject.service.serviceImpl;

import com.vitkovskaya.finalProject.entity.Cleaner;
import com.vitkovskaya.finalProject.entity.Client;
import com.vitkovskaya.finalProject.entity.User;
import com.vitkovskaya.finalProject.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class ClientServiceImplTest {
    private final static Logger logger = LogManager.getLogger();
    private final static long ID = 2L;
    ClientServiceImpl clientService;


    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in clientServiceImpl");
        clientService = new ClientServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in clientServiceImpl");
        clientService = null;
    }

    @Test (dataProvider = "Client")
    public void testUpdateClient(Client client) throws ServiceException {
        boolean actual = clientService.updateClient(client);
        assertTrue(actual);
    }

    @Test
    public void testFindBlockedClients() throws ServiceException {
        assertNotNull(clientService.findBlockedClients());
    }

    @DataProvider(name = "Client")
    private static Object[] clientProvider() {
        return new Object[]{
                new Client(11L, "Алена", "Караневич", "Тимирязева 123-1",
                        "375441234567"
                )
        };
    }

    @Test(dataProvider = "Client")
    public void testFindByIdPositive(Client client) throws ServiceException {
        Client actual = clientService.findById(11L).get();
        assertEquals(actual, client);
    }

    @Test(dataProvider = "Client")
    public void testFindByIdNegative(Client client) throws ServiceException {
        Client actual = clientService.findById(3L).get();
        assertNotEquals(actual, client);
    }

    @Test
    public void testFindAllClients() throws ServiceException {
        assertTrue(!clientService.findAllClients().isEmpty());
    }
}