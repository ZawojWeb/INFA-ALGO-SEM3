package com.faktury;

import java.util.ArrayList;

public class Invoice {
    ArrayList<Item> items = new ArrayList<>();
    String sellerName;
    Long sellerNip;
    String sellerAdress;
    String buyerName;
    Long buyerNip;
    String buyerAdress;
    String pdfName;
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
