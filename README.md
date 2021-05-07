# StockExchange
StockExchange service to buy and sell stocks, matching service to match highest buy value to lowest sell value for a stock, show on the console that stock buy and sell price


## Order

Order class is the super class 

**Attributes:** orderId,time,stock,type,price and quantity 
BuyOrder and SellOrder are base classes that inherit from the Order class


## StockExchangeService

**Attributes:**
PriorityQueue<BuyOrder> buyOrders : To store the BuyOrders on the basis of max price priority and then time priority
PriorityQueue<SellOrder> sellOrders : To store the SellOrders on the basis of max price priority and then time priority
  
### addOrders()

To read the stock exchange orders from stockexchangeorders.txt file **This file is the test file, replace with your tests** and create the specific type of order(BuyOrder/SellOrder) and call the matchBuyOrder() for buyOrders and calls matchSellOrders() function for sellOrders

### matchBuyOrder(BuyOrder buyOrder)

To get a matching SellOrder for this buyOrder from the sellOrders priority queue, update the quantity for each type of order.
If buyOrders quantity is greater than sellOrder's quantity then matchBuyOrder is again called with remaining buy quantity.


**Conditions:**
- sellOrder's price has to be less than the buyOrders price
- sellOrder's price has to be minimum

Print the matched buy sell order

### matchSellOrder(SellOrder sellOrder)

To get a matching buyorder for this SellOrder from the buyOrders priority queue, update the quantity for each type of order.
If sellOrders quantity is greater than buyOrder's quantity then matchSellOrder is again called with remaining sell quantity.


**Conditions:**
- sellOrder's price has to be less than the buyOrders price
- sellOrder's price has to be minimum

Print the matched buy sell order




