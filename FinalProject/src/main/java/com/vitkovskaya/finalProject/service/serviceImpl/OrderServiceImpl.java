package com.vitkovskaya.finalProject.service.serviceImpl;


import com.vitkovskaya.finalProject.dao.DaoException;
import com.vitkovskaya.finalProject.dao.EntityTransaction;
import com.vitkovskaya.finalProject.dao.impl.CleaningDaoImpl;
import com.vitkovskaya.finalProject.dao.impl.OrderDaoImpl;
import com.vitkovskaya.finalProject.entity.*;
import com.vitkovskaya.finalProject.service.CleaningListAction;
import com.vitkovskaya.finalProject.service.OrderService;
import com.vitkovskaya.finalProject.service.ServiceException;
import com.vitkovskaya.finalProject.util.DataTimeParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@code OrderServiceImpl} class
 * contains methods to provide logic operations with {@code Order} objects.
 */

public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public List<Order> findAllClientOrders(Long id) throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<Order> orderList;
        transaction.begin(orderDao, cleaningDao);
        try {
            orderList = orderDao.findAllOrderByClientId(id);
            for (Order order: orderList) {
                List<CleaningItem> itemList = cleaningDao.findCleaningsInOrder(order.getId());
                order.setCleaningList(itemList);
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return orderList;
    }

    @Override
    public List<Order> findAllCleanerOrders(Long id) throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<Order> orderList;
        transaction.begin(orderDao, cleaningDao);
        try {
            orderList = orderDao.findAllOrderByCleanerId(id);
            for (Order order: orderList) {
                List<CleaningItem> itemList = cleaningDao.findCleaningsInOrder(order.getId());
                order.setCleaningList(itemList);
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return orderList;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        CleaningDaoImpl cleaningDao = new CleaningDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<Order> orderList;
        transaction.begin(orderDao, cleaningDao);
        try {
            orderList = orderDao.findAll();
            for (Order order: orderList) {
                List<CleaningItem> itemList = cleaningDao.findCleaningsInOrder(order.getId());
                order.setCleaningList(itemList);
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return orderList;
    }

    @Override
    public Map<Long, Long> createOrder(User user, String date, String paymentType, String comment,
                            List<CleaningItem> orderCleaningList) throws ServiceException {
        DataTimeParser parser = new DataTimeParser();
        CleaningListAction action = new CleaningListAction();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        Map<Long, Long> resultMap = new HashMap<>();
        Set<Long> cleanerIdSet = action.getCleanersId(orderCleaningList);
        long createdId;
        transaction.begin(orderDao);
        try {
            for (Long cleanerId : cleanerIdSet) {
                List<CleaningItem> subList = orderCleaningList.stream().filter((p) ->
                        p.getCleaning().getCleanerId() == cleanerId).collect(Collectors.toList());
                BigDecimal orderSumSub = action.calculateTotalSum(subList);
                Order order = new Order(orderSumSub, LocalDateTime.now(), parser.getTime(date), OrderStatus.NEW,
                        PaymentType.valueOf(paymentType.toUpperCase()), false, comment);
                logger.log(Level.DEBUG, "FROM CREATE ORDER");
                orderDao.create(order);
                createdId = order.getId();
                resultMap.put(cleanerId, createdId);
                orderDao.linkOrderClient(user.getUserId(), createdId, subList.size());
                orderDao.linkOrderCleaner(cleanerId, createdId);
                for (CleaningItem item : subList) {
               //     Cleaning cleaning = ;
                    orderDao.linkOrderCleaning(item.getCleaning().getId(), createdId, item.getQuantity());
                }
                transaction.commit();
            }
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while committing transaction", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return resultMap;
    }

      @Override
    public boolean changeOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException {
        boolean updated;
        OrderDaoImpl orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginNoTransaction(orderDao);
        try {
            updated = orderDao.updateOrderStatus(orderId, orderStatus);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }

    @Override
    public boolean changePaymentStatus(long orderId, OrderStatus orderStatus) throws ServiceException {
        boolean updated;
        OrderDaoImpl orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(orderDao);
        try {
            orderDao.updateOrderStatus(orderId, orderStatus);
            updated = orderDao.updatePaymentStatus(orderId);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return updated;
    }

}
