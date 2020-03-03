package com.vitkovskaya.finalProject.entity;

import java.util.List;
import java.util.Objects;

public class Client extends User {


    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephoneNumber;
    private List<Order> orderList;

    public Client() {
        super();
    }

    public Client(long id, String firstName, String lastName, String address, String telephoneNumber) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
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
        Client client = (Client) o;
        if (id != client.id) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(client.telephoneNumber) : client.telephoneNumber != null)
            return false;
        return orderList != null ? orderList.equals(client.orderList) : client.orderList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (orderList != null ? orderList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client: ");
        builder.append("id: ").append(id);
        builder.append(", firstName: ").append(firstName);
        builder.append(", lastName: ").append(lastName);
        builder.append(", address: ").append(address);
        builder.append(", telephoneNumber ").append(telephoneNumber);
        return builder.toString();
    }
}
