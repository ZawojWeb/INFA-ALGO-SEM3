package com.faktury;

import java.util.ArrayList;
/**
* Class of invoice
*
* @param  sellerName  names the company that issues the invoice
* @param  sellerNip nip the company that issues the invoice
* @param  sellerAdress adress the company that issues the invoice
* @param  buyerName  a name of buyer's company
* @param  buyerNip the most important param, bsc this is kind of id in our app
* @param  buyerAdress a adress of buyer's company   
* @param  totalPriceOfAllItems method wchih count the sum of product's cost

*/
public class Invoice {
    ArrayList<Item> items = new ArrayList<>();
    String sellerName;
    Long sellerNip;
    String sellerAdress;
    String buyerName;
    Long buyerNip;
    String buyerAdress;
    double totalPriceOfAllItems;

    public Invoice(ArrayList<Item> items, String sellerName, Long sellerNip2, String sellerAdress, String buyerName,
            Long buyerNip2, String buyerAdress) {
        this.items = items;
        this.sellerName = sellerName;
        this.sellerNip = sellerNip2;
        this.sellerAdress = sellerAdress;
        this.buyerAdress = buyerAdress;
        this.buyerName = buyerName;
        this.buyerNip = buyerNip2;

    }

}
