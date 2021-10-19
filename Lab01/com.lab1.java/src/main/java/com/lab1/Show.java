package com.lab1;

import java.util.ArrayList;

public class Show {
    public static void showFunction(ArrayList<String> allTasks) {
        if (allTasks.size() > 0) {
            System.out.println("YOUOR TO DO :");
            for (int i = 0; i < allTasks.size(); i++) {
                System.out.println("- " + allTasks.get(i).replaceAll("_", " "));
            }
            System.out.println("");
        } else {
            System.out.println("!!! You dont have any tasks. !!!");
        }

    }
}
