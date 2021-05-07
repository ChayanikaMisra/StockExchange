package com.stockExchange.models;

import java.time.LocalTime;

public class SellOrder extends Order {
    public SellOrder(String orderId, LocalTime time, String stock, String type, float price, int quantity) {
        super();
        this.orderId=orderId;
        this.time=time;
        this.stock=stock;
        this.type=type;
        this.price=price;
        this.quantity=quantity;
    }
}
