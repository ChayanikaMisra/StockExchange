package com.stockExchange.models;

import java.time.LocalTime;

public class BuyOrder extends Order{
    public BuyOrder(String orderId, LocalTime time, String stock, String type, float price, int quantity) {
        super();
        this.orderId=orderId;
        this.time=time;
        this.stock=stock;
        this.type=type;
        this.price=price;
        this.quantity=quantity;
    }
}
