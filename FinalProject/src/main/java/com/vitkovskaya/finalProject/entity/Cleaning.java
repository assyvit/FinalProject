package com.vitkovskaya.finalProject.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Cleaning extends Entity {
    private long id;
    private String name;
    private BigDecimal price;
    private CleaningType cleaningType;
    private String description;
    private int quantity;
    private boolean isAvailable;
    private long cleanerId;

    public Cleaning() {
    }

    public Cleaning(long id, String name, BigDecimal price, CleaningType cleaningType, String description,
                    int quantity, boolean isAvailable, long cleanerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cleaningType = cleaningType;
        this.description = description;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.cleanerId = cleanerId;
    }

    public Cleaning(long id, String name, BigDecimal price, CleaningType cleaningType, String description,
                    int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cleaningType = cleaningType;
        this.description = description;
        this.quantity = quantity;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CleaningType getCleaningType() {
        return cleaningType;
    }

    public void setCleaningType(CleaningType cleaningType) {
        this.cleaningType = cleaningType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(long cleanerId) {
        this.cleanerId = cleanerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Cleaning cleaning = (Cleaning) o;
        return id == cleaning.id &&
                name != null ? name.equals(cleaning.name) : cleaning.name == null &&
                price != null ? cleaning.price.compareTo(price) == 0 : cleaning.price == null &&
                cleaningType != null ? cleaningType.equals(cleaning.cleaningType) : cleaning.cleaningType == null &&
                description != null ? description.equals(cleaning.description) : cleaning.description == null &&
                quantity == cleaning.quantity &&
                isAvailable == cleaning.isAvailable &&
                cleanerId == cleaning.cleanerId;
    }


    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (cleaningType != null ? cleaningType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + (int) (cleanerId ^ (cleanerId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cleaning: ");
        builder.append(", id: ").append(id);
        builder.append(", price: ").append(price);
        builder.append(", cleaningType: ").append(cleaningType);
        builder.append(", description: ").append(description);
        builder.append(", quantity: ").append(quantity);
        builder.append(", isAvailable: ").append(isAvailable);
        builder.append(", cleanerId: ").append(cleanerId);
        return builder.toString();
    }
}
