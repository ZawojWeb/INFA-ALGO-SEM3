package com.faktury;

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
        this.priceofTax = ((qty * price * 0.27) * 100.0) / 100.0;
        this.totalPrice = qty * price;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Qty: " + this.qty + " Price for one: " + this.price + " The 23% of tax: "
                + this.priceofTax + " Total price for product: " + this.totalPrice;
    }

}
