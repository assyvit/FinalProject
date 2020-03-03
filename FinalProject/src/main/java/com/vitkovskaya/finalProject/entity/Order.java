package com.vitkovskaya.finalProject.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order extends Entity {

    /**
     * The {@code Order} class
     * is an entity that represents table 'order' in the database.
     * <p>
     * Overrides equals(), hashcode(), toString() methods.
     */
    private long id;
    private BigDecimal orderSum;
    private LocalDateTime incomingDate;
    private LocalDateTime executeDate;
    private OrderStatus orderStatus;
    private PaymentType paymentType;
    private boolean paymentFulfilled;
    private String comment;
    private List<CleaningItem> cleaningList;

    public Order() {
    }


    public Order(BigDecimal orderSum, LocalDateTime incomingDate, LocalDateTime executeDate, OrderStatus orderStatus,
                 PaymentType paymentType, boolean paymentFulfilled, String comment) {
        this.orderSum = orderSum;
        this.incomingDate = incomingDate;
        this.executeDate = executeDate;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.paymentFulfilled = paymentFulfilled;
        this.comment = comment;
    }

    public Order(BigDecimal orderSum, LocalDateTime incomingDate, LocalDateTime executeDate, OrderStatus orderStatus,
                 PaymentType paymentType, boolean paymentFulfilled) {
        this.orderSum = orderSum;
        this.incomingDate = incomingDate;
        this.executeDate = executeDate;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.paymentFulfilled = paymentFulfilled;
    }


    public Order(long id, BigDecimal orderSum, LocalDateTime incomingDate, LocalDateTime executeDate, OrderStatus orderStatus,
                 PaymentType paymentType, boolean paymentFulfilled, String comment) {
        this.id = id;
        this.orderSum = orderSum;
        this.incomingDate = incomingDate;
        this.executeDate = executeDate;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.paymentFulfilled = paymentFulfilled;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public LocalDateTime getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(LocalDateTime incomingDate) {
        this.incomingDate = incomingDate;
    }

    public LocalDateTime getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDateTime executeDate) {
        this.executeDate = executeDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPaymentFulfilled() {
        return paymentFulfilled;
    }

    public void setPaymentFulfilled(boolean paymentFulfilled) {
        this.paymentFulfilled = paymentFulfilled;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CleaningItem> getCleaningList() {
        return cleaningList;
    }

    public void setCleaningList(List<CleaningItem> cleaningList) {
        this.cleaningList = cleaningList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null)
            if (this.getClass() != o.getClass()) {
                return false;
            }
        Order order = (Order) o;
        if (id != order.id) {
            return false;
        }
        if (paymentFulfilled != order.paymentFulfilled) {
            return false;
        }
        if (orderSum != null ? !orderSum.equals(order.orderSum) : order.orderSum != null) {
            return false;
        }
        if (incomingDate != null ? !incomingDate.equals(order.incomingDate) : order.incomingDate != null) {
            return false;
        }
        if (executeDate != null ? !executeDate.equals(order.executeDate) : order.executeDate != null) {
            return false;
        }
        if (orderStatus != order.orderStatus) {
            return false;
        }
        if (paymentType != order.paymentType) {
            return false;
        }
        if (comment != null ? !comment.equals(order.comment) : order.comment != null) {
            return false;
        }
        return cleaningList != null ? cleaningList.equals(order.cleaningList) : order.cleaningList == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        result = 31 * result + (incomingDate != null ? incomingDate.hashCode() : 0);
        result = 31 * result + (executeDate != null ? executeDate.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (paymentFulfilled ? 1 : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (cleaningList != null ? cleaningList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Order: ").append(id);
        sb.append("id: ").append(id);
        sb.append(", orderSum: ").append(orderSum);
        sb.append(", incomingDate: ").append(incomingDate);
        sb.append(", executeDate: ").append(executeDate);
        sb.append(", orderStatus: ").append(orderStatus);
        sb.append(", paymentType: ").append(paymentType);
        sb.append(", paymentFulfilled: ").append(paymentFulfilled);
        sb.append(", comment: ").append(comment);
        return sb.toString();
    }
}
