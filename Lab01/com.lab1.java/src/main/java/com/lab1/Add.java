/**
 * This file is for add new item to list
 * */ 
package com.lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class Add {
    
    public static void addFunction( ArrayList<String> allTask){
        
        Scanner in = new Scanner(System.in);
        String newTask = new String();
        Boolean corectName = true;
        do {
            System.out.println("Add task, put the content and then click enter");
            newTask = in.nextLine();
            if(allTask.contains(newTask)){
                System.out.println("!!! Oh you already make thisk task. Try other name !!!");
            }else{
                allTask.add(newTask);
                corectName = false;
            }
        } while (corectName);

        System.out.println("New task was correctly added: " +  newTask);
        return;
    }
}
