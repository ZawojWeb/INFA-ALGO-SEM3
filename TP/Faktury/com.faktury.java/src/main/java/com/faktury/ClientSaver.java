package com.faktury;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
/**
* Class which saveing client into pdf
*
* @param  client  this is the saving client
* @param  buyerNip this is use as id of pdf
*/

public class ClientSaver {
    public static void Saver(Client client, Long buyerNip) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        File home = FileSystemView.getFileSystemView().getHomeDirectory();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(home.getAbsolutePath() + "/invoice/clients/" + client.buyerNip + ".pdf"));
        document.open();

        document.add(new Paragraph(client.toString()));

        document.close();
        writer.close();
    }
}
