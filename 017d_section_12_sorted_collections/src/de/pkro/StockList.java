package de.pkro;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if item already in stock, adjust quantity
            if (inStock != item) {
                item.adjustStock(inStock.availableQuantity());
            }

            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
/*        StockItem inStock = list.getOrDefault(item, null);
        if (inStock != null && inStock.availableQuantity() >= quantity && quantity > 0) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;*/

        StockItem stockItem = list.get(item);
        if(stockItem != null && quantity > 0) {
            return stockItem.finalizeStock(quantity);
        }
        return 0;
    }

    public int reserveStock(String item, int quantity) {
        StockItem stockItem = list.getOrDefault(item, null);
        if(item != null && quantity > 0) {
            return stockItem.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity) {
        StockItem stockItem = list.get(item);
        if(item != null && quantity > 0) {
            return stockItem.unreserveStock(-quantity);
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    // avoids returning the actual StockItem objects that could potentially be modified
    // even when using Collections.unmodifiableMap
    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String, StockItem> entry: list.entrySet()) {
            prices.put(entry.getKey(), entry.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List\n");
        double totalCost = 0.0;
        for (String key : list.keySet()) {
            StockItem stockItem = list.get(key);
            double itemValue = stockItem.availableQuantity() * stockItem.getPrice();
            s.append(stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value is " + String.format("%.2f", itemValue) + "\n");
            totalCost += itemValue;
        }
        s.append("Total stock value " + totalCost);
        return s.toString();
    }
}
