package de.pkro;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        stockList.addStock(new StockItem("Wurst", 3.99, 30));
        stockList.addStock(new StockItem("Käse", 3.99, 12));
        stockList.addStock(new StockItem("cake", 1.99, 200));
        stockList.addStock(new StockItem("car", 24000, 1));
        stockList.addStock(new StockItem("cup", 3.99, 7));
        stockList.addStock(new StockItem("cup", 3.99, 200));
        stockList.addStock(new StockItem("door", 99.99, 1));

        System.out.println(stockList);

        Basket basket = new Basket("Peers basket");
        sellItem(basket, "cup", 150);
        sellItem(basket, "car", 1);


        System.out.println(basket);
        System.out.println(stockList);

        StockItem temp = new StockItem("I don't exist", 0.99);
        // stockList.Items().put(temp.getName(), temp); // UnsupportedOperationException as Items() returns an unmodifieableMap

        stockList.Items().get("car").adjustStock(20000); // Object in the mao can STILL be updated
        System.out.println(stockList);
        System.out.println(stockList.priceList());

    }

    public static int sellItem(Basket basket, String item, int amount) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("Item not in stock");
            return 0;
        }
        if( stockList.sellStock(item, amount) != 0) {
            basket.addToBasket(stockItem, amount);
            return amount;
        }
        return 0;
    }
}
