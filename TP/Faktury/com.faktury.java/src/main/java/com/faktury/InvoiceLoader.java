package com.faktury;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
* 
*  Load all bouer's inoices. Load is based on NIP.
* All data is loaded from pdf file
*/

public class InvoiceLoader {
    static ArrayList<String> InvoiceListToLoad = new ArrayList<>();

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

                    String lines[] = pdfFileInText.split("\\r?\\n");
                    for (String line : lines) {
                        System.out.println(line);
                    }
                    System.out.println("\n");
                }

            }
        }

    }
}
