package com.faktury;

import java.util.ArrayList;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class InvoiceCreator {
    static ArrayList<Item> items = new ArrayList<>();
    static String sellerName;
    static Integer sellerNip;
    static String sellerAdress;
    static String buyerName;
    static Integer buyerNip;
    static String buyerAdress;
    static String pdfName;
    static double totalPriceOfAllItems;

    public static void createInvoice() {

        TextIO textIO = TextIoFactory.getTextIO();

        sellerName = textIO.newStringInputReader().read("Seller comapny name");
        sellerNip = textIO.newIntInputReader().read("Seller comapny NIP");
        sellerAdress = textIO.newStringInputReader().read("Seller comapny adress");
        buyerName = textIO.newStringInputReader().read("Buyer comapny name");
        buyerNip = textIO.newIntInputReader().read("Buyer comapny NIP");
        buyerAdress = textIO.newStringInputReader().read("Buyer comapny adress");

        String condition;
        System.out.println("You have to add at least one product");
        do {
            String itemName = textIO.newStringInputReader().read("Item Name");
            int itemQty = textIO.newIntInputReader().withMinVal(1).read("Quantity");
            String itemPrice = textIO.newStringInputReader().read("Price per item");
            double itemPriceDouble = Double.parseDouble(itemPrice);
            items.add(new Item(itemName, itemQty, itemPriceDouble));
            condition = textIO.newStringInputReader().read("Add next item y-yes");

        } while (condition.equals("y"));

        Invoice invoice = new Invoice(items, sellerName, sellerNip, sellerAdress, buyerName, buyerNip, buyerAdress);
        InvoiceSave.Save(invoice);

    }
}
