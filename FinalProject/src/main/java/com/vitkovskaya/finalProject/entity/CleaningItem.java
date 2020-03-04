package com.vitkovskaya.finalProject.entity;

public class CleaningItem {
    private Cleaning cleaning;
    private int quantity;
    private String cleanerName;
    public CleaningItem() {
    }
    public CleaningItem(Cleaning cleaning, int quantity) {
        this.cleaning = cleaning;
        this.quantity = quantity;
    }
    public CleaningItem(Cleaning cleaning, int quantity, String cleanerName) {
        this.cleaning = cleaning;
        this.quantity = quantity;
        this.cleanerName = cleanerName;
    }
    public Cleaning getCleaning() {
        return cleaning;
    }
    public void setCleaning(Cleaning cleaning) {
        this.cleaning = cleaning;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCleanerName() {
        return cleanerName;
    }
    public void setCleanerName(String cleanerName) {
        this.cleanerName = cleanerName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if ( o == null) {return false;}
        if (this.getClass() != o.getClass()) {return false;}
        CleaningItem item = (CleaningItem) o;
        if (quantity != item.quantity) {return false;}
        if (cleaning != null ? !cleaning.equals(item.cleaning) : item.cleaning != null) {return false;}
        return cleanerName != null ? cleanerName.equals(item.cleanerName) : item.cleanerName == null;
    }
    @Override
    public int hashCode() {
        int result = cleaning != null ? cleaning.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (cleanerName != null ? cleanerName.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CleaningItem: ");
        builder.append(" cleaning: ").append(cleaning);
        builder.append(", quantity: ").append(quantity);
        builder.append(", cleanerName: ").append(cleanerName);
        return builder.toString();
    }
}
