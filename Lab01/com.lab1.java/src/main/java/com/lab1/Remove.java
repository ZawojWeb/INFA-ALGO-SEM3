/**
 * This class is for deleting item from list
 * */ 
package com.lab1;

import java.util.ArrayList;

public class Remove {
    
    public static void removeFunction(ArrayList<String> allTasks){
        Boolean removedAction = true;
        String taskToDelete;

        do {
            System.out.println("Put the name of task which you want delete");
            System.out.print("> ");
            taskToDelete = App.in.nextLine().replaceAll(" ", "_");

            if(allTasks.contains(taskToDelete)){
                allTasks.remove(taskToDelete);
                removedAction = false;
            }else{
                System.out.println("!!! Ops I can't find todos with this name !!! Try again.");
                Show.showFunction(allTasks);
            }
            
        } while (removedAction);

        System.out.println("Correctly removed task: " + taskToDelete.replaceAll(" ", "_"));

    }
}
