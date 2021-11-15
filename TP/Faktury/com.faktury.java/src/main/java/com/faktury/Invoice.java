package com.faktury;

import java.util.ArrayList;

public class Invoice {
    ArrayList<Item> items = new ArrayList<>();
    String sellerName;
    Integer sellerNip;
    String sellerAdress;
    String buyerName;
    Integer buyerNip;
    String buyerAdress;
    String pdfName;
    double totalPriceOfAllItems;

    Invoice(ArrayList<Item> items, String sellerName, Integer sellerNip, String sellerAdress, String buyerName,
            Integer buyerNip, String buyerAdress) {
        this.items = items;
        this.sellerName = sellerName;
        this.sellerNip = sellerNip;
        this.sellerAdress = sellerAdress;
        this.buyerAdress = buyerAdress;
        this.buyerName = buyerName;
        this.buyerNip = buyerNip;

    }

}
