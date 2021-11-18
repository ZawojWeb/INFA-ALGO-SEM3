package com.faktury;

/**
* Class of item
*
* @param  name  its just a name
* @param  qty  quantity of product
* @param  price price with Tax
* @param  totalPrice sum of prices for one item
* @param  priceofTax  all tax's

*/

public class Item {
    private String name;
    private int qty;
    private double price;
    private double priceofTax;
    private double totalPrice;

    public Item(String name, int qty, double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.priceofTax = calculateTax(qty, price);
        this.totalPrice = qty * price;
    }
/**
* This format how will be save item in pdf
*
*/

    @Override
    public String toString() {
        return "Name: " + this.name + " Qty: " + this.qty + " Price for one: " + this.price + " The 23% of tax: "
                + this.priceofTax + " Total price for product: " + this.totalPrice;
    }
/**
* 
* @return the tax 

*/

    public static double calculateTax(int qty, double price) {
        return ((qty * price * 0.23) * 100.0) / 100.0;

    }

}
