package com.faktury;

import java.nio.file.Paths;

import javax.swing.filechooser.FileSystemView;

public class Folder {
    public static void createFolder() {
        String folderName = "invoice";
        if (Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(), folderName) != null) {
            Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(), folderName).toFile()
                    .mkdir();
        }
        ;

    }
}
