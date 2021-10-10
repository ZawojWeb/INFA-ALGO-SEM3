package com.lab1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *! TODO: create global varible (Array) for list od tasks
 */

public class App 
{
    // public static String[] allTasks = {"Task1", "Task2"};
    // public static String[] allTasks = {"Task1", "Task2", ""};
    public static ArrayList<String> allTasks = new ArrayList<String>();
    public static String option;
    
    public static void main( String[] args )
    {
        Boolean runApp = true;
        allTasks.add("Task1");
        allTasks.add("Task2");
        Scanner in = new Scanner(System.in);

        System.out.println("Hello in your TODO list");
         do {
            System.out.println("What you want to do?");
            option = in.nextLine();
            switch (option.toLowerCase()) {
                case "add":
                    Add.addFunction(allTasks); 
                    option = "show";
                    break;

               case "remove":
                Remove.removeFunction();
                /**
                    * !TODO remove one
                    * * write item to remove Function have to be in other class
                    * ? Check that if taks with this name exist
                    * */  
                   break;

               case "show":
               System.out.println("YOU TO DO :");
                    for (int i = 0; i < allTasks.size(); i++) {
                        System.out.println("- " + allTasks.get(i));
                    }
                   break;

               case "save":
                       /**
                        * !TODO exit with save
                        * */  
                   break;

               case "load":
                       /**
                         * !TODO load
                         * */  
                   break;

               case "close":
                    runApp = false;
                   break;

                default:
                    break;
            }
            // System.out.println(runApp);
         } while (runApp);

    }

}
