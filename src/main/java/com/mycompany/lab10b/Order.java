/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab10b;

 
import java.util.LinkedHashMap;
import java.util.Map;
 

/**
 *
 * @author Alex
 */
public class Order {
    
        private static final Map<String, Double> ENTREES = new LinkedHashMap<>();
    private static final Map<String, Double> DRINKS = new LinkedHashMap<>();
    
    static {
        ENTREES.put("Chicken Salad", 6.50);
        ENTREES.put("Turkey Sandwich", 6.75);
        ENTREES.put("Vegetable Wrap", 6.00);
        ENTREES.put("Grilled Cheese", 5.00);
 
        DRINKS.put("Coffee", 1.50);
        DRINKS.put("Tea", 1.50);
        DRINKS.put("Soda", 2.00);
        DRINKS.put("Juice", 1.75);
    }
 
    // The order idea
    private String entree;          
    private String drink;          
    private String specialRequest = "";
    private boolean water;
 
    public static Iterable<String> getEntreeNames() {
        return ENTREES.keySet();
    }
 
    public double getEntreePrice() {
        return entree == null ? 0.0 : ENTREES.getOrDefault(entree, 0.0);
    }
 
    public double getDrinkPrice() {
        return drink == null ? 0.0 : DRINKS.getOrDefault(drink, 0.0);
    }
 
    // calcs the entree and drink
    public double getTotal() {
        return getEntreePrice() + getDrinkPrice();
    }
 
    // Both an entree and a drink are required to place an order
    public boolean isComplete() {
        return entree != null && drink != null;
    }
 
    public void clear() {
        entree = null;
        drink = null;
        specialRequest = "";
        water = false;
    }
 
    //getters/setters
    public String getEntree() { return entree; }
    public void setEntree(String entree) { this.entree = entree; }
 
    public String getDrink() { return drink; }
    public void setDrink(String drink) { this.drink = drink; }
 
    public String getSpecialRequest() { return specialRequest; }
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest == null ? "" : specialRequest;
    }
 
    public boolean isWater() { return water; }
    public void setWater(boolean water) { this.water = water; }
}