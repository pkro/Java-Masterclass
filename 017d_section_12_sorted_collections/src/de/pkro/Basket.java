package de.pkro;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        list = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    /****
     * @param item
     * @param quantity
     * @return previous quantity of item in basket
     */
    public int addToBasket(StockItem item, int quantity) {
        if(item != null && quantity > 0) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, quantity + inBasket);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        if(item != null && quantity > 0) {
            int inBasket = list.getOrDefault(item, 0);
            int newQuantity = inBasket - quantity;
            if(newQuantity > 0) {
                list.put(item, newQuantity);
                return quantity;
            } else if(newQuantity == 0){
                list.remove(item);
                return quantity;
            }
        }
        return 0; // indicate an error as 0 would suggest a correct amount was removed
    }

    public void clearBasket() {
        list.clear();
    }
    /**
     * @return unmodifiable Map of items in basket
     */
    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + (list.size()>1?" items":" item")+"\n";
        double totalCost = 0.0;
        for(Map.Entry<StockItem, Integer> item: list.entrySet()) {
            totalCost += item.getKey().getPrice() * item.getValue();
            s += item.getKey() + ", " + item.getValue() + " purchased\n";
        }

        return s +  "Total cost: " + String.format("%.2f", totalCost);
    }
}
