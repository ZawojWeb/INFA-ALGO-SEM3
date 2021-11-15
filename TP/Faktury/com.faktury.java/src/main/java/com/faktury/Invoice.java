package com.faktury;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class Invoice {
    static ArrayList<Item> items = new ArrayList<>();
    static String sellerName;
    static String sellerNip;
    static String sellerAdress;
    static String buyerName;
    static String buyerNip;
    static String buyerAdress;
    static String pdfName;

    private Invoice(ArrayList<Item> items, String sellerName, String sellerNip, String sellerAdress, String buyerName,
            String buyerNip, String buyerAdress) {
        Invoice.items = items;
        Invoice.sellerName = sellerName;
        Invoice.sellerNip = sellerNip;
        Invoice.sellerAdress = sellerAdress;
        Invoice.buyerAdress = buyerAdress;
        Invoice.buyerName = buyerName;
        Invoice.buyerNip = buyerNip;

    }

    public static void createInvoice() {

        TextIO textIO = TextIoFactory.getTextIO();

        sellerName = textIO.newStringInputReader().read("Seller comapny name");
        sellerNip = textIO.newStringInputReader().read("Seller comapny NIP");
        sellerAdress = textIO.newStringInputReader().read("Seller comapny adress");
        buyerName = textIO.newStringInputReader().read("Buyer comapny name");
        buyerNip = textIO.newStringInputReader().read("Buyer comapny NIP");
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
        invoice.Save();

    }

    public static void Save() {
        Document document = new Document();
        try {
            TextIO textIO = TextIoFactory.getTextIO();
            pdfName = textIO.newStringInputReader().read("Name of invoice file");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfName + ".pdf"));
            document.open();

            document.add(new Paragraph("Seller:"));
            document.add(new Paragraph("Name: " + sellerName));
            document.add(new Paragraph("Adress: " + sellerAdress));
            document.add(new Paragraph("Nip:" + sellerNip));

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Buyer:"));
            document.add(new Paragraph("Name: " + buyerName));
            document.add(new Paragraph("Adress: " + buyerAdress));
            document.add(new Paragraph("Nip:" + buyerNip));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Products:"));
            // Print the products
            for (Item item : items) {
                document.add(new Paragraph(item.toString()));
            }

            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
