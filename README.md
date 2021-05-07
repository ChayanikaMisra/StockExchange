# StockExchange
StockExchange service to buy and sell stocks, matching service to match highest buy value to lowest sell value for a stock, show on the console that stock buy and sell price

# Steps to run:
  1. add testcases in [this file](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/stockexchangeorders.txt)
  2. run the [StockExchangeService](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/services/StockExchangeService.java)

# Design and Description of classes/functions

## [Order](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/models/Order.java)

Order class is the super class 

**Attributes:** orderId,time,stock,type,price and quantity 
[BuyOrder](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/models/BuyOrder.java) and [SellOrder](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/models/SellOrder.java) are base classes that inherit from the Order class


## [StockExchangeService](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/services/StockExchangeService.java)

**Attributes:**
PriorityQueue<BuyOrder> buyOrders : To store the BuyOrders on the basis of max price priority and then time priority
PriorityQueue<SellOrder> sellOrders : To store the SellOrders on the basis of max price priority and then time priority
  
### [addOrders()](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/services/StockExchangeService.java#L25)

To read the stock exchange orders from [stockexchangeorders.txt](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/stockexchangeorders.txt) file **This file is the test file, replace with your tests** and create the specific type of order(BuyOrder/SellOrder) and call the matchBuyOrder() for buyOrders and calls matchSellOrders() function for sellOrders

### [matchBuyOrder(BuyOrder buyOrder)](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/services/StockExchangeService.java#L54)

To get a matching SellOrder for this buyOrder from the sellOrders priority queue, update the quantity for each type of order.
If buyOrders quantity is greater than sellOrder's quantity then matchBuyOrder is again called with remaining buy quantity.


**Conditions:**
- sellOrder's price has to be less than the buyOrders price
- sellOrder's price has to be minimum

Print the matched buy sell order

### [matchSellOrder(SellOrder sellOrder)](https://github.com/ChayanikaMisra/StockExchange/blob/master/src/main/java/com/stockExchange/services/StockExchangeService.java#L92)

To get a matching buyorder for this SellOrder from the buyOrders priority queue, update the quantity for each type of order.
If sellOrders quantity is greater than buyOrder's quantity then matchSellOrder is again called with remaining sell quantity.


**Conditions:**
- sellOrder's price has to be less than the buyOrders price
- sellOrder's price has to be minimum

Print the matched buy sell order




