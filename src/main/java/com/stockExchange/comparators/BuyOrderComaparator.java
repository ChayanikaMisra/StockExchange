package com.stockExchange.comparators;

import com.stockExchange.models.BuyOrder;

import java.time.LocalTime;
import java.util.Comparator;

public class BuyOrderComaparator implements Comparator<BuyOrder> {
    public int compare(BuyOrder buyOrder1, BuyOrder buyOrder2) {
        if (buyOrder1.getPrice() < buyOrder2.getPrice())
            return 1;
        if (buyOrder1.getPrice() > buyOrder2.getPrice())
            return -1;
        if(buyOrder1.getPrice() == buyOrder2.getPrice()) {
            LocalTime buyOrder1time = buyOrder1.getTime();
            LocalTime buyOrder2time = buyOrder2.getTime();
            if (buyOrder1time.compareTo(buyOrder2time)>0)
                return 1;
            else
                return -1;
        }

        return 0;
    }

}
