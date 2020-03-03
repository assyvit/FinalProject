package com.vitkovskaya.finalProject.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cleaner extends User {
    private long cleanerId;
    private String firstName;
    private String lastName;
    private String address;
    private String telephoneNumber;
    private List<Order> orderList;
    private List<Cleaning> cleaningList;

    public Cleaner() {
        super();
    }

    public Cleaner(UserRole userRole, long cleanerId, String firstName, String lastName, String address,
                   String telephoneNumber) {
        super();
        this.cleanerId = cleanerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public Cleaner(long cleanerId, String firstName, String lastName, String address,
                   String telephoneNumber) {

        this.cleanerId = cleanerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }


    public long getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(long cleanerId) {
        this.cleanerId = cleanerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override // FIXME: 29.02.2020 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Cleaner cleaner = (Cleaner) o;
        if (cleanerId != cleaner.cleanerId) return false;
        return firstName != null ? firstName.equals(cleaner.firstName) : cleaner.firstName == null &&
                lastName != null ? lastName.equals(cleaner.lastName) : cleaner.lastName == null &&
                address != null ? address.equals(cleaner.address) : cleaner.address == null &&
                telephoneNumber != null ?
                telephoneNumber.equals(cleaner.telephoneNumber) : cleaner.telephoneNumber == null;
    }

    @Override // FIXME: 29.02.2020 
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) (cleanerId ^ (cleanerId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + ( orderList != null ? orderList.hashCode() : 0);
        result = 31 * result + (cleaningList != null ? cleaningList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cleaner: ");
        builder.append(", id: ").append(cleanerId);
        builder.append(", firstName: ").append(firstName);
        builder.append(", lastName: ").append(lastName);
        builder.append(", address: ").append(address);
        builder.append(", telephoneNumber: ").append(telephoneNumber);
        return builder.toString();
    }
}
