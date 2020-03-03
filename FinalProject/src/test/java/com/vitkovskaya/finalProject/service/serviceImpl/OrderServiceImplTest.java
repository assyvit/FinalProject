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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.*;

public class OrderServiceImplTest {
    private final static Logger logger = LogManager.getLogger();
    private final static long CLEANER_ID_FULL = 2L;
    private final static long CLIENT_ID_FULL = 3L;
    private final static long CLEANER_ID_EMPTY = 78L;
    private final static long CLIENT_ID_EMPTY = 26L;
    OrderServiceImpl orderService;

    @BeforeClass
    public void setUp() throws ServiceException {
        logger.info("Start test in orderServiceTest");
        orderService = new OrderServiceImpl();

    }

    @AfterClass
    public void tearDown() {
        logger.info("End test in orderServiceTest");
        orderService = null;
    }

    @Test
    public void testFindAllClientOrdersPositive() throws ServiceException {
        assertTrue(!orderService.findAllClientOrders(CLIENT_ID_FULL).isEmpty());
    }

    @Test
    public void testFindAllClientOrdersNegative() throws ServiceException {
        assertTrue(orderService.findAllClientOrders(CLIENT_ID_EMPTY).isEmpty());
    }

    @Test
    public void testFindAllCleanerOrdersPositive() throws ServiceException {
        assertTrue(!orderService.findAllCleanerOrders(CLEANER_ID_FULL).isEmpty());
    }

    @Test
    public void testFindAllCleanerOrdersNegative() throws ServiceException {
        assertTrue(orderService.findAllCleanerOrders(CLEANER_ID_EMPTY).isEmpty());
    }

    @Test
    public void testFindAllOrders() throws ServiceException {
        assertTrue(!orderService.findAllOrders().isEmpty());
    }

    @DataProvider(name = "OrderLit")
    private static Object[][] orderProvider() {
        return new Object[][]{{new ArrayList<CleaningItem>() {{
            add(new CleaningItem(new Cleaning(1L, "Уборка  комнаты", new BigDecimal(45.00).setScale(2),
                    CleaningType.CLEANING, "Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли",
                    1, true, 7L), 2));
            add(new CleaningItem(new Cleaning(5L, "Мойка окон", new BigDecimal(15.00).setScale(2),
                    CleaningType.WASHING, "Мойка окон, рам, подоконников",
                    1, true, 8L), 2));
        }}, new User(UserRole.CLIENT, 4L, "client2@gmail.com", "e84ceafb2fadee8f4290972263face2f1d7887b9", true,
                "/uploads/ebbad530-3107-4dc7-a24a-9b28d4a92636.png")
        }


        };
    }

    @Test(dataProvider = "OrderLit")
    public void testCreateOrder(ArrayList<CleaningItem> list, User user) throws ServiceException {
        Map<Long, Long> actual = orderService.createOrder(user, "2020-03-03 15:00", "cash",
                "comment", list);
        assertTrue(!actual.isEmpty());
    }

    @Test
    public void testChangeOrderStatus() throws ServiceException {
        boolean actual = orderService.changeOrderStatus(3L, OrderStatus.NEW);
        assertTrue(actual);
    }

    @Test
    public void testChangePaymentStatus() throws ServiceException {
        boolean actual = orderService.changePaymentStatus(3L);
        assertTrue(actual);
    }
}