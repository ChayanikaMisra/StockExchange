package com.stockExchange.comparators;

import com.stockExchange.models.SellOrder;

import java.time.LocalTime;
import java.util.Comparator;

public class SellOrderComparator implements Comparator<SellOrder> {
    public int compare(SellOrder sellOrder1, SellOrder sellOrder2) {
        if (sellOrder1.getPrice() > sellOrder2.getPrice())
            return 1;
        if (sellOrder1.getPrice() < sellOrder2.getPrice())
            return -1;
        if (sellOrder1.getPrice() == sellOrder2.getPrice()) {
            LocalTime sellOrder1time = sellOrder1.getTime();
            LocalTime sellOrder2time = sellOrder2.getTime();
            if (sellOrder1time.compareTo(sellOrder2time) > 0)
                return 1;
            else
                return -1;
        }

        return 0;
    }
}
