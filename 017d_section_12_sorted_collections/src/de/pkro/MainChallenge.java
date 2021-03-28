package de.pkro;

import java.util.Map;

public class MainChallenge {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        stockList.addStock(new StockItem("Wurst", 3.99, 30));
        stockList.addStock(new StockItem("cup", 3.99, 200));
        stockList.addStock(new StockItem("door", 99.99, 1));

        System.out.println(stockList);

        Basket basket = new Basket("Peers basket");
        sellItem(basket, "cup", 150);

        System.out.println(basket);
        System.out.println(stockList);

        checkout(basket);
        System.out.println(stockList);
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("Item not in stock");
            return 0;
        }

        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("Item not in stock");
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkout(Basket basket) {
        for(Map.Entry<StockItem, Integer> item: basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
