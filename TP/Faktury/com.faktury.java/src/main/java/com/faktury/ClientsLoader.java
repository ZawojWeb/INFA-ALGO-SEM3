package com.faktury;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ClientsLoader {
    static ArrayList<String> allClients;

    public static void Load() throws IOException {
        File home = FileSystemView.getFileSystemView().getHomeDirectory();
        File folder = new File(home.getAbsoluteFile() + "/invoice/clients");
        File[] listOfFiles = folder.listFiles();
        allClients = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                allClients.add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                continue;
            }
        }

        for (String file : allClients) {
            try (PDDocument document = Loader.loadPDF(new File(folder + "\\" + file))) {

                document.getClass();

                if (!document.isEncrypted()) {

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);

                    PDFTextStripper tStripper = new PDFTextStripper();

                    String pdfFileInText = tStripper.getText(document);
                    System.out.println("------------------------");

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
