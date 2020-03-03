package com.vitkovskaya.finalProject.service;

import com.vitkovskaya.finalProject.command.ConstantName;

import com.vitkovskaya.finalProject.entity.Cleaning;
import com.vitkovskaya.finalProject.entity.CleaningItem;

import java.math.BigDecimal;
import java.util.*;

public class CleaningListAction {
    /**
     * Search if the cleaning is present in the order list.
     *
     * @param cleaningId to look if cleaning is present in the list
     * @return optional (@code CleaningItem)
     */
    private Optional<CleaningItem> findItemInList(List<CleaningItem> cleaningList, long cleaningId) {
        Optional<CleaningItem> itemOptional = Optional.empty();
        for (CleaningItem item : cleaningList) {
            if (item.getCleaning().getId() == cleaningId) {
                itemOptional = Optional.of(item);
            }
        }
        return itemOptional;
    }

    /**
     * Add (@code CleaningItem) into the cleaning list, if it is not present in it.
     * Otherwise quantity of the cleaning is changed to new one.
     *
     * @param cleaning to be added in the cart
     * @param quantity amount to be added to the cart
     */
    public void addItem(List<CleaningItem> cleaningList, Cleaning cleaning, int quantity) {
        Optional<CleaningItem> itemOptional = findItemInList(cleaningList, cleaning.getId());
        if (itemOptional.isPresent()) {
            CleaningItem item = itemOptional.get();
            item.setQuantity(quantity);
        } else {
            CleaningItem newItem = new CleaningItem(cleaning, quantity);
            cleaningList.add(newItem);
        }
    }

    /**
     * Deletes a (@code CleaningItem) from the cleaning list
     *
     * @param cleaningId id of cleaning to look for and delete.
     */
    public void removeItem(List<CleaningItem> cleaningList, long cleaningId) {
        Iterator<CleaningItem> iterator = cleaningList.iterator();
        while (iterator.hasNext()) {
            long id = iterator.next().getCleaning().getId();
            if (id == cleaningId) {
                iterator.remove();
            }
        }
    }

    /**
     * Calculates the total value for the order.
     *
     * @return Total order sum.
     */
    public BigDecimal calculateTotalSum(List<CleaningItem> cleaningList) {
        BigDecimal totalSum = new BigDecimal(ConstantName.ZERO_VALUE);
        for (CleaningItem item : cleaningList) {
            BigDecimal price = item.getCleaning().getPrice();
            int quantity = item.getQuantity();
            totalSum = totalSum.add(price.multiply(new BigDecimal(quantity)));
        }
        return totalSum;
    }

    /**
     * Find all uniq cleaners id of the cleanings in the list .
     *
     * @param cleaningList list with ordered cleanings
     * @return Set
     */
    public Set<Long> getCleanersId(List<CleaningItem> cleaningList) {
        Set<Long> cleanerIdSet = new HashSet<>();
        //cleaningList.getList().stream().map(p -> p.getCleaning().getCleanerId()).collect(Collectors.toSet());
        for (CleaningItem item : cleaningList) {
            cleanerIdSet.add(item.getCleaning().getCleanerId());
        }
        return cleanerIdSet;
    }
}
