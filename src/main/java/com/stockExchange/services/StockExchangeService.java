package com.stockExchange.services;

import com.stockExchange.comparators.BuyOrderComaparator;
import com.stockExchange.comparators.SellOrderComparator;
import com.stockExchange.models.BuyOrder;
import com.stockExchange.models.SellOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StockExchangeService {
    PriorityQueue<BuyOrder> buyOrders = new PriorityQueue<BuyOrder>(new BuyOrderComaparator());
    PriorityQueue<SellOrder> sellOrders = new PriorityQueue<SellOrder>(new SellOrderComparator());

    public static void main(String[] args) {
        StockExchangeService stockExchangeService = new StockExchangeService();
        stockExchangeService.addOrders();
    }

    public void addOrders() {
        try {
            File ordersFile = new File("src/main/java/com/stockExchange/filename.txt");
            Scanner ordersReader = new Scanner(ordersFile);
            while (ordersReader.hasNextLine()) {
                String[] data = ordersReader.nextLine().split(" ");
                String orderId = data[0];
                LocalTime time = LocalTime.parse(data[1]);
                String stock = data[2];
                String type = data[3];
                float price = Float.parseFloat(data[4]);
                int quantity = Integer.parseInt(data[5]);

                matchOrders(orderId, time, stock, type, price, quantity);
            }
            ordersReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Please add the stock orders file at location: src/main/java/com/stockExchange/");
            e.printStackTrace();
        }
    }

    private void matchOrders(String orderId, LocalTime time, String stock, String type, float price, int quantity) {
        if (type.equals("buy")) {
            BuyOrder buyOrder = new BuyOrder(orderId, time, stock, type, price, quantity);
            matchBuyOrder(buyOrder);
        }
        if (type.equals("sell")) {
            SellOrder sellOrder = new SellOrder(orderId, time, stock, type, price, quantity);
            matchSellOrder(sellOrder);
        }

    }


    private void matchBuyOrder(BuyOrder buyOrder) {
        if (sellOrders.isEmpty())
            buyOrders.add(buyOrder);
        else {

            List<SellOrder> sellOrdersList = new ArrayList<>();
            SellOrder matchSellOrder = sellOrders.peek();
            while (!sellOrders.isEmpty()) {
                matchSellOrder = sellOrders.remove();
                if (matchSellOrder.getPrice() < buyOrder.getPrice())
                    break;
                else {
                    sellOrdersList.add(matchSellOrder);
                }
            }
            sellOrders.addAll(sellOrdersList);

            int sellQuantity = matchSellOrder.getQuantity();
            int buyQuantity = buyOrder.getQuantity();
            if (sellQuantity > buyQuantity) {
                int remQuantity = sellQuantity - buyQuantity;
                matchSellOrder.setQuantity(remQuantity);
                System.out.println(buyOrder.getOrderId() + " " + matchSellOrder.getPrice() + " " + buyQuantity + " " + matchSellOrder.getOrderId());
            } else if (buyQuantity > sellQuantity) {
                int remQuantity = buyQuantity - sellQuantity;
                buyOrder.setQuantity(remQuantity);
                sellOrders.remove();
                buyOrders.add(buyOrder);
                System.out.println(buyOrder.getOrderId() + " " + matchSellOrder.getPrice() + " " + sellQuantity + " " + matchSellOrder.getOrderId());

            } else {
                sellOrders.remove();
                System.out.println(buyOrder.getOrderId() + " " + matchSellOrder.getPrice() + " " + sellQuantity + " " + matchSellOrder.getOrderId());

            }
        }
    }

    private void matchSellOrder(SellOrder sellOrder) {
        if (buyOrders.isEmpty())
            sellOrders.add(sellOrder);
        else {
            List<BuyOrder> buyOrdersList = new ArrayList<>();
            BuyOrder matchBuyOrder = buyOrders.peek();
            while (!buyOrders.isEmpty()) {
                matchBuyOrder = buyOrders.peek();
                if (matchBuyOrder.getPrice() >= sellOrder.getPrice())
                    break;
                else {
                    buyOrdersList.add(buyOrders.remove());
                }
            }
            buyOrders.addAll(buyOrdersList);
            int buyQuantity = matchBuyOrder.getQuantity();
            int sellQuantity = sellOrder.getQuantity();
            if (buyQuantity > sellQuantity) {
                int remQuantity = buyQuantity - sellQuantity;
                matchBuyOrder.setQuantity(remQuantity);
                System.out.println(matchBuyOrder.getOrderId() + " " + sellOrder.getPrice() + " " + sellQuantity + " " + sellOrder.getOrderId());
            } else if (buyQuantity < sellQuantity) {
                int remQuantity = sellQuantity - buyQuantity;
                sellOrder.setQuantity(remQuantity);
                buyOrders.remove();
                sellOrders.add(sellOrder);
                System.out.println(matchBuyOrder.getOrderId() + " " + sellOrder.getPrice() + " " + buyQuantity + " " + sellOrder.getOrderId());
                matchSellOrder(sellOrder);
            } else {
                buyOrders.remove();
                System.out.println(matchBuyOrder.getOrderId() + " " + sellOrder.getPrice() + " " + sellQuantity + " " + sellOrder.getOrderId());
            }
        }

    }
}
