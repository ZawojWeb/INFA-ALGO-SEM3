package com.faktury;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
/**
* This is class create pdf include on created invoice in InvoiceCreate.java
*
* @param  invoice this is object wchih will be saved in pdf
*/

public class InvoiceSave {
    public static void Save(Invoice invoice) {
        Document document = new Document();
        try {
            TextIO textIO = TextIoFactory.getTextIO();
            String pdfName = textIO.newStringInputReader().read("Name of invoice file");
            File home = FileSystemView.getFileSystemView().getHomeDirectory();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
                    home.getAbsolutePath() + "/invoice/" + pdfName + "-" + invoice.buyerNip + ".pdf"));
            document.open();

            document.add(new Paragraph("Seller:"));
            document.add(new Paragraph("Name: " + invoice.sellerName));
            document.add(new Paragraph("Adress: " + invoice.sellerAdress));
            document.add(new Paragraph("Nip:" + invoice.sellerNip));

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Buyer:"));
            document.add(new Paragraph("Name: " + invoice.buyerName));
            document.add(new Paragraph("Adress: " + invoice.buyerAdress));
            document.add(new Paragraph("Nip:" + invoice.buyerNip));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Products:"));
            // Print the products
            for (Item item : invoice.items) {
                document.add(new Paragraph(item.toString()));
            }
            document.add(new Paragraph("Total price: " + invoice.totalPriceOfAllItems));

            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // System.exit(0);
    }
}
