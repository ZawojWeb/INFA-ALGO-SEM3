package com.faktury;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

/**
* The core class for create the invoice with use inputs. And call save method
*
* @param pdfName as suggest this is the name of pdf
* @param items array of all items wchih bought the customer
*/

public class InvoiceCreator {
    static ArrayList<Item> items = new ArrayList<>();
    static String sellerName;
    static Long sellerNip;
    static String sellerAdress;
    static String buyerName;
    static Long buyerNip;
    static String buyerAdress;
    static String pdfName;
    static double totalPriceOfAllItems;

    public static void createInvoice() throws FileNotFoundException, DocumentException {

        TextIO textIO = TextIoFactory.getTextIO();

        sellerName = textIO.newStringInputReader().read("Seller comapny name");
        sellerNip = textIO.newLongInputReader().read("Seller comapny NIP");
        sellerAdress = textIO.newStringInputReader().read("Seller comapny adress");
        buyerName = textIO.newStringInputReader().read("Buyer comapny name");
        buyerNip = textIO.newLongInputReader().read("Buyer comapny NIP");
        buyerAdress = textIO.newStringInputReader().read("Buyer comapny adress");

        Client client = new Client(buyerName, buyerNip, buyerAdress);
        ClientSaver.Saver(client, buyerNip);
        String condition;
        System.out.println("You have to add at least one product");
        do {
            String itemName = textIO.newStringInputReader().read("Item Name");
            int itemQty = textIO.newIntInputReader().withMinVal(1).read("Quantity");
            String itemPrice = textIO.newStringInputReader().read("Price per item");
            double itemPriceDouble = Double.parseDouble(itemPrice);
            items.add(new Item(itemName, itemQty, itemPriceDouble));
            condition = textIO.newStringInputReader().read("Add next item y-yes n-no");

        } while (condition.equals("y"));

        Invoice invoice = new Invoice(items, sellerName, sellerNip, sellerAdress, buyerName, buyerNip, buyerAdress);
        InvoiceSave.Save(invoice);

    }
}
