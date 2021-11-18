package com.faktury;

import java.nio.file.Paths;

import javax.swing.filechooser.FileSystemView;

public class Folder {
    
    /**
    * createFolder  method is for creating folders on desktop. Is invoked in App class before each run the app.
    */
    public static void createFolder() {
        String folderName = "invoice";
        if (Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(), folderName)
                .toFile() != null) {
            Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(), folderName).toFile()
                    .mkdir();
        }
        ;
        if (Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/" + folderName,
                "clients").toFile() != null) {
            Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/" + folderName,
                    "clients").toFile().mkdir();
        }
        ;

    }
}
