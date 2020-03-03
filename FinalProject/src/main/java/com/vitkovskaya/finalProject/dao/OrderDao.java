package com.vitkovskaya.finalProject.dao;


import com.vitkovskaya.finalProject.entity.Order;
import com.vitkovskaya.finalProject.entity.OrderStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {

    List<Order> findAllFulfilledOrders() throws DaoException;

    List<Order> findAllCancelledOrders() throws DaoException;

    List<Order> findOrderByIncomingDate(LocalDate dateIncoming) throws DaoException;

    List<Order> findOrderByExecuteDate(LocalDate dateExecuting) throws DaoException;

    List<Order> findAllUnpaidOrders() throws DaoException;

    List<Order> findAllPaidOrders() throws DaoException;

    List<Order> findMinOrderSum() throws DaoException;

    List<Order> findMaxOrderSum() throws DaoException;

    List<Order> findOrderSumFromTo(int sumFrom, int sumTo) throws DaoException;

    /**
     * Updates a row in the table using order id
     * with paymentStatus true
     *
     * @param orderId a order id
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if  occurs database access error
     */
    boolean updatePaymentStatus(long orderId) throws DaoException;

    /**
     * Updates a row in the table using order id
     * with new order status value
     *
     * @param orderId a order id
     * @param status  a new {@code OrderStatus} value
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if  occurs database access error
     */
    boolean updateOrderStatus(long orderId, OrderStatus status) throws DaoException;

    /**
     * Inserts into the table 'client_orders' a row that represents
     * connection between {@code Client} and {@code Order} and total quantity of {@code Cleaning}
     * in new order.
     *
     * @param clientId           a client id
     * @param orderId            a order id
     * @param cleaningCountTotal a {@code Order} object to insert into the table 'order'
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if a database access error occurs
     */
    boolean linkOrderClient(long clientId, long orderId, int cleaningCountTotal) throws DaoException;

    /**
     * Inserts into the table 'cleaner_orders' a row that represents
     * connection between {@code Cleaner} and {@code Order}
     *
     * @param cleanerId a cleaner id
     * @param orderId   a order id
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if a database access error occurs
     */
    boolean linkOrderCleaner(long cleanerId, long orderId) throws DaoException;
    /**
     * Inserts into the table 'cleaning_in_order' rows that represents
     * connection between {@code Order} and {@code Cleaning} and quantity of each cleaning
     * in new order.
     *
     * @param cleaningId         a cleaning id
     * @param orderId            a order id
     * @param cleaningCount      a quantity of each cleaning
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if a database access error occurs
     */
    boolean linkOrderCleaning(long cleaningId, long orderId, int cleaningCount) throws DaoException;

    /**
     * Gets all rows from the table 'order' by {@code Client} clientId,
     * returns them as a list of {@code Order} objects
     *
     * @return a list contains {@code Order} or empty list, if client don't have orders,
     * not null
     * @throws DaoException if a database access error occurs
     */
    List<Order> findAllOrderByClientId(Long clientId) throws DaoException;

    /**
     * Gets all rows from the table 'order' by {@code Cleaner} clientId,
     * returns them as a list of {@code Order} objects
     *
     * @return a list contains {@code Order} or empty list, if cleaner don't have orders,
     * not null
     * @throws DaoException if a database access error occurs
     */
    List<Order> findAllOrderByCleanerId(Long cleanerId) throws DaoException;
}
