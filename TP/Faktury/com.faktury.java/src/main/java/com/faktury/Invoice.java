package com.faktury;

import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.io.IOException;
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
    static ArrayList<String> InvoiceListToLoad = new ArrayList<>();
    static String sellerName;
    static Integer sellerNip;
    static String sellerAdress;
    static String buyerName;
    static Integer buyerNip;
    static String buyerAdress;
    static String pdfName;
    static double totalPriceOfAllItems;

    private Invoice(ArrayList<Item> items, String sellerName, Integer sellerNip, String sellerAdress, String buyerName,
            Integer buyerNip, String buyerAdress) {
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
        invoice.Save();

    }

    public void Save() {
        Document document = new Document();
        try {
            TextIO textIO = TextIoFactory.getTextIO();
            pdfName = textIO.newStringInputReader().read("Name of invoice file");
            File home = FileSystemView.getFileSystemView().getHomeDirectory();

            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(home.getAbsolutePath() + "/invoice/" + pdfName + "-" + buyerNip + ".pdf"));
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
            document.add(new Paragraph("Total price: " + totalPriceOfAllItems));

            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void Load(Integer NIP) throws IOException {
        File home = FileSystemView.getFileSystemView().getHomeDirectory();
        File folder = new File(home.getAbsoluteFile() + "/invoice/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(NIP.toString())) {
                InvoiceListToLoad.add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                continue;
            }
        }
        for (String file : InvoiceListToLoad) {
            try (PDDocument document = Loader.loadPDF(new File(folder + "\\" + file))) {

                document.getClass();

                if (!document.isEncrypted()) {

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);

                    PDFTextStripper tStripper = new PDFTextStripper();

                    String pdfFileInText = tStripper.getText(document);
                    System.out.println("\n------------------------");

                    // split by whitespace
                    String lines[] = pdfFileInText.split("\\r?\\n");
                    for (String line : lines) {
                        System.out.println(line);
                    }

                }

            }
        }
        System.exit(0);

    }

}
