package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.entity.*;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> findAllClientOrders(Long id) throws ServiceException;

    List<Order> findAllCleanerOrders(Long id) throws ServiceException;

    /**
     * Gets all orders from a database.
     *
     * @return a list contains {@code Order}, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> findAllOrders() throws ServiceException;

    Map<Long, Long> createOrder(User user, String date, String paymentType, String comment,
                                List<CleaningItem> orderCleaningList) throws ServiceException;

    boolean changeOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException;

    boolean changePaymentStatus(long orderId, OrderStatus orderStatus) throws ServiceException;
}
