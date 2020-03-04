package com.vitkovskaya.finalProject.dao;

import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.CleaningItem;
import java.util.List;

public interface CleaningDao {
    /**
     * Gets a row from the table using cleaner id,
     * builds cleaning, adds it to List and returns List<Cleaning> object that represents this id
     *
     * @param cleanerId a cleaner id
     * @return a {@code List} of {@code Cleaning}, or empty List if no cleaning is founded by id in the table
     * @throws DaoException if a database access error occurs
     */
    List<Cleaning> findByCleanerId(long cleanerId) throws DaoException;

    /**
     * Updates a row in the table using cleaning id
     * with new values - availableStatus
     *
     * @param cleaningId      - a cleaning id
     * @param availableStatus - a new status value (is cleaning available for ordering - true, otherwise - false)
     * @return {@code true} if row was updated, otherwise {@code false}
     * @throws DaoException if occurs database access error
     */
    boolean updateCleaningStatus(long cleaningId, boolean availableStatus) throws DaoException;

    /**
     * Returns a List that has a {@code ItemInCart}.
     * List is built from the rows in two tables 'cleaning', 'cleaning_in_order'.
     * List contains ItemInCart only for one order.
     *
     * @param orderId order id
     * @return a List contains {@code ItemInCart} presented in the order and their quantity, not null
     * @throws DaoException if a database access error occurs
     */
     List<CleaningItem> findCleaningsInOrder(Long orderId) throws DaoException;
}
